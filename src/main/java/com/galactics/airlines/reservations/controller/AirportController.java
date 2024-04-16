package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.service.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/add")
    public ResponseEntity<AirportDTOResponse> addAirport(@RequestBody AirportDTORequest airportDTORequest) throws GalaticsAirlinesException {
        log.info("Adding airport: {}", airportDTORequest.toString());
        return ResponseEntity.ok(airportService.addAirport(airportDTORequest));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<AirportDTOResponse> updateAirport(@PathVariable Long id, @RequestBody AirportDTORequest airportDTORequest) throws GalaticsAirlinesException {
        log.info("Updating airport: {}", airportDTORequest.toString());
        return ResponseEntity.ok(airportService.updateAirport(id, airportDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        try {
            airportService.deleteAirport(id);
            log.info("Airport Deleted");
            return ResponseEntity.accepted().build();
        } catch (GalaticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
