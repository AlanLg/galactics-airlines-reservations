package com.galactics.airlines.reservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GalacticsAirlinesException extends RuntimeException {

    public GalacticsAirlinesException(String message) {
        super(message);
    }

    public GalacticsAirlinesException(String message, Throwable cause) {
        super(message, cause);
    }
}