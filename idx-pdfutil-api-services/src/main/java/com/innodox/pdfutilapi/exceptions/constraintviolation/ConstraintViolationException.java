package com.innodox.pdfutilapi.exceptions.constraintviolation;

/**
 * Base for all cases when the request for the demanded operation is syntactically good but
 * there are semantically problems like referencing other resources they not exist. Tipically
 * - behind the scenes - there is a (foreign key) contraint violation.
 * (see: HTTP 422)
 */
public class ConstraintViolationException extends RuntimeException {

    public static final String TEMPLATE = "The referenced resource does not exist: %d";

    public ConstraintViolationException(String message) {
        super(message);
    }

    public static ConstraintViolationException create(long id) {
        return new ConstraintViolationException(String.format(TEMPLATE, id));
    }
}
