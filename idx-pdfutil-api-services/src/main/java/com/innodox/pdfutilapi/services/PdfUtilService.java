package com.innodox.pdfutilapi.services;

import com.innodox.pdfutilapi.exceptions.PdfUtilServiceException;
import com.innodox.pdfutilapi.exceptions.notfound.ResourceNotFoundException;
import com.innodox.pdfutilapi.models.folding.FoldingLine;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class PdfUtilService {

    private final BaseColor BASE_COLOR = new BaseColor(127, 127, 127);

    public byte[] addFoldingLines(byte[] document, List<FoldingLine> foldingLines, String requestId) {
        // TODO return meta information so that it can be logged in the service layer
        try {
            PdfReader reader = new PdfReader(document);
            int numberOfPages = reader.getNumberOfPages();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, bos);

            PdfContentByte pdfContentByte;
            for (int i = 1; i <= numberOfPages; i++) {
                log.debug("Adding lines to page: {}", i);
                pdfContentByte = stamper.getOverContent(i);
                pdfContentByte.saveState();
                pdfContentByte.setColorStroke(BASE_COLOR);
                for (FoldingLine foldingLine : foldingLines) {
                    drawLine(pdfContentByte, foldingLine);
                }
                pdfContentByte.stroke();
                pdfContentByte.restoreState();
            }
            stamper.close();
            reader.close();
            log.debug("Line drawing completed.");
            return bos.toByteArray();
        } catch (Exception e) {
            throw new PdfUtilServiceException(e);
        }
    }

    private void drawLine(PdfContentByte pdfContentByte, FoldingLine foldingLine) {
        float startX = foldingLine.getStartPoint().getX();
        float startY = foldingLine.getStartPoint().getY();
        float endX = foldingLine.getEndPoint().getX();
        float endY = foldingLine.getEndPoint().getY();

        log.trace("Drawing line from x: {} y: {} to x: {} y: {}", startX, startY, endX, endY);
        pdfContentByte.moveTo(startX, startY);
        pdfContentByte.lineTo(endX, endY);
    }

    public int getPageCount(byte[] document, String requestId) {
        log.debug("Getting pages count of document. Data length: {}", document.length);
        try {
            PdfReader reader = new PdfReader(document);
            int numberOfPages = reader.getNumberOfPages();
            reader.close();

            log.debug("Page count: {}", numberOfPages);

            return numberOfPages;
        } catch (Exception e) {
            throw new PdfUtilServiceException(e);
        }
    }

    public byte[] getPage(byte[] document, int pageNumber, String requestId) {
        log.debug("Getting page from document. Page: {}, document data length: {}", pageNumber, document.length);
        try {
            PdfReader reader = new PdfReader(document);
            int numberOfPages = reader.getNumberOfPages();

            validatePageNumber(pageNumber, numberOfPages);

            reader.selectPages(String.valueOf(pageNumber));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, outputStream);

            stamper.close();
            reader.close();

            log.debug("Pdf created from page: {}", pageNumber);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new PdfUtilServiceException(e);
        }
    }

    private void validatePageNumber(int pageNumber, int numberOfPages) {
        log.debug("Validating page number. Requested page: {}, number of pages: {}", pageNumber, numberOfPages);
        if (pageNumber <= 0 || pageNumber > numberOfPages) {
            throw new ResourceNotFoundException(
                    String.format(
                            "Page not found: %d",
                            pageNumber
                    )
            );
        }
        log.debug("Page number is valid. Requested page: {}, number of pages: {}", pageNumber, numberOfPages);
    }
}
