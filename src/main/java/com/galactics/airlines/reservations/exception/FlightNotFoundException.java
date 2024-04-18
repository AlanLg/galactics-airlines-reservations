package com.galactics.airlines.reservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends GalacticsAirlinesException {

    public FlightNotFoundException() {
        super("Flight not found");
    }
}
