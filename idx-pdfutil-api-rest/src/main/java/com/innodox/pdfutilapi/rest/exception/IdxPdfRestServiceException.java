package com.innodox.pdfutilapi.rest.exception;

public class IdxPdfRestServiceException extends RuntimeException {
    public IdxPdfRestServiceException() {
    }

    public IdxPdfRestServiceException(String message) {
        super(message);
    }

    public IdxPdfRestServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdxPdfRestServiceException(Throwable cause) {
        super(cause);
    }

    public IdxPdfRestServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
