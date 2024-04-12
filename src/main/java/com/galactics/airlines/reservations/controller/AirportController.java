package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.service.AirplaneService;
import com.galactics.airlines.reservations.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/add")
    public ResponseEntity<AirportDTOResponse> addFlight(@RequestBody AirportDTORequest airportDTORequest) throws GalaticsAirlinesException {
        return ResponseEntity.ok(airportService.addAirport(airportDTORequest));
    }
}
