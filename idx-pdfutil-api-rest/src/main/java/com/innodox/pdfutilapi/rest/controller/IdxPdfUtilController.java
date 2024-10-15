package com.innodox.pdfutilapi.rest.controller;

import com.innodox.pdfutilapi.rest.api.generated.IdxPdfUtilApi;
import com.innodox.pdfutilapi.rest.model.generated.FoldingLine;
import com.innodox.pdfutilapi.rest.model.generated.GetPageCount200Response;
import com.innodox.pdfutilapi.rest.service.IdxPdfRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IdxPdfUtilController implements IdxPdfUtilApi {

    private final IdxPdfRestService idxPdfRestService;

    @Override
    public ResponseEntity<Resource> addFoldingLines(MultipartFile document, List<FoldingLine> foldingLines, String requestId) {
        return ResponseEntity.ok(idxPdfRestService.addFoldingLines(document, foldingLines, requestId));
    }

    @Override
    public ResponseEntity<Resource> getPage(MultipartFile document, Integer pageNumber, String requestId) {
        return ResponseEntity.ok(idxPdfRestService.getPage(document, pageNumber, requestId));
    }

    @Override
    public ResponseEntity<GetPageCount200Response> getPageCount(MultipartFile document, String requestId) {
        return ResponseEntity.ok(idxPdfRestService.getPageCount(document, requestId));
    }
}
