package com.innodox.pdfutilapi.exceptions;

public class PdfUtilServiceException extends RuntimeException {
    public PdfUtilServiceException() {
    }

    public PdfUtilServiceException(String message) {
        super(message);
    }

    public PdfUtilServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PdfUtilServiceException(Throwable cause) {
        super(cause);
    }

    public PdfUtilServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
