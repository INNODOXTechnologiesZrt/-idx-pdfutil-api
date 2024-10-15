package com.innodox.pdfutilapi.exceptions.notfound;

/**
 * Base for all cases when "NOT FOUND" (see HTTP 404) exception should be thrown
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
