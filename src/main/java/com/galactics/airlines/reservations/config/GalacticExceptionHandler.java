package com.galactics.airlines.reservations.config;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GalacticExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {GalacticsAirlinesException.class})
    protected ResponseEntity<Object> handleGalacticsAirlinesException(GalacticsAirlinesException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
