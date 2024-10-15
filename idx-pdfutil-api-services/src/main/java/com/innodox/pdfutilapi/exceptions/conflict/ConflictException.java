package com.innodox.pdfutilapi.exceptions.conflict;

/**
 * Base for all cases when the request for the demanded operation is syntactically good but
 * there are semantically problems like inconsistent data or changed data states due
 * to parallel client works. (see: HTTP 409)
 *
 * "The HTTP 409 status code (Conflict) indicates that the request could not be processed
 * because of conflict in the request, such as the requested resource is not in the expected
 * state, or the result of processing the request would create a conflict within the resource"
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

}
