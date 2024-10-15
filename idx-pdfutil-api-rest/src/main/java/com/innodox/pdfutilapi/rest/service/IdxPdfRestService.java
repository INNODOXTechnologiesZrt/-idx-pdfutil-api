package com.innodox.pdfutilapi.rest.service;

import com.innodox.pdfutilapi.rest.exception.IdxPdfRestServiceException;
import com.innodox.pdfutilapi.rest.mappers.FoldingLineMapper;
import com.innodox.pdfutilapi.rest.model.generated.FoldingLine;
import com.innodox.pdfutilapi.rest.model.generated.GetPageCount200Response;
import com.innodox.pdfutilapi.services.PdfUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class IdxPdfRestService {

    private final PdfUtilService pdfUtilService;
    private final FoldingLineMapper foldingLineMapper;

    public Resource addFoldingLines(MultipartFile file, List<FoldingLine> foldingLines, String requestId) {
        log.debug("addFoldingLines() CALLED. Request id: " + requestId);
        try {
            byte[] bytes = pdfUtilService.addFoldingLines(
                    file.getBytes(),
                    foldingLines.stream().map(foldingLineMapper::fromRestDtoToModel).toList(),
                    requestId
            );
            return new ByteArrayResource(bytes);
        } catch (Exception e) {
            throw new IdxPdfRestServiceException(e);
        }
    }

    public Resource getPage(MultipartFile document, Integer pageNumber, String requestId) {
        log.debug("getPage() CALLED. Request id: " + requestId);
        try {
            byte[] bytes = pdfUtilService.getPage(document.getBytes(), pageNumber, requestId);
            return new ByteArrayResource(bytes);
        } catch (Exception e) {
            throw new IdxPdfRestServiceException(e);
        }
    }

    public GetPageCount200Response getPageCount(MultipartFile document, String requestId) {
        log.debug("getPageCount() CALLED. Request id: " + requestId);
        try {
            int pageCount = pdfUtilService.getPageCount(document.getBytes(), requestId);
            GetPageCount200Response getPageCount200Response = new GetPageCount200Response();
            getPageCount200Response.setPageCount(pageCount);
            return getPageCount200Response;
        } catch (Exception e) {
            throw new IdxPdfRestServiceException(e);
        }
    }

}
