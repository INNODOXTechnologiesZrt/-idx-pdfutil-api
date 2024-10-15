package com.innodox.pdfutilapi.rest.config;

import com.innodox.pdfutilapi.exceptions.conflict.ConflictException;
import com.innodox.pdfutilapi.exceptions.constraintviolation.ConstraintViolationException;
import com.innodox.pdfutilapi.exceptions.notfound.EntityNotFoundException;
import com.innodox.pdfutilapi.exceptions.notimplemented.NotImplementedException;
import com.innodox.pdfutilapi.rest.model.generated.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class,})
    protected ResponseEntity<Object> handleEntityNotFoundException(
            RuntimeException exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConflictException.class,})
    protected ResponseEntity<Object> handleConflictException(
            RuntimeException exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class,})
    protected ResponseEntity<Object> handleConstraintViolationException(
            RuntimeException exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class,})
    protected ResponseEntity<Object> handleIllegalArgumentException(
            RuntimeException exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotImplementedException.class,})
    protected ResponseEntity<Object> handleNotImplementedException(
            RuntimeException exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = {AccessDeniedException.class,})
    protected ResponseEntity<Object> handleAccessDeniedException(
            RuntimeException exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {Exception.class,})
    protected ResponseEntity<Object> handleOtherException(
            Exception exception,
            WebRequest request
    ) {
        return handleExceptionWithStatusCode(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        log.error("Handling: ", ex);
        return handleExceptionWithStatusCode(ex, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<Object> handleExceptionWithStatusCode(
            Exception exception, HttpStatus httpStatus
    ) {
        log.error("Handling: ", exception);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ErrorResponse().message(exception.getMessage()), httpHeaders, httpStatus);
    }
}
