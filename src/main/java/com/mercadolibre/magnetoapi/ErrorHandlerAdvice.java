package com.mercadolibre.magnetoapi;

import com.mercadolibre.magnetoapi.controller.dtos.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlerAdvice.class);

    /**
     * Handles spring validations.
     */
    @ExceptionHandler
    public ResponseEntity<Error> handleMethodArgumentNotValidException(IllegalArgumentException exception) {
        LOG.error(exception.getMessage(), exception);
        Error error = new Error();
        error.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}