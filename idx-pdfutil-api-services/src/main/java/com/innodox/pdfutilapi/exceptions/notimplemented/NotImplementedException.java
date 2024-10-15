package com.innodox.pdfutilapi.exceptions.notimplemented;

/**
 * Base for all cases when the requested operation is not implemented (see HTTP 501)
 */
public class NotImplementedException extends RuntimeException {
    public NotImplementedException(String message) {
        super(message);
    }
}
