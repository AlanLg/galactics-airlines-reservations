package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.FilterFlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.service.FlightService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/flight")
@OpenAPIDefinition(info = @Info(title = "Galatics Airlines API", version = "v1"))
@SecurityRequirement(name = "basicAuth")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<FlightDTOResponse> addFlight(@RequestBody FlightDTORequest flightDTORequest) throws GalaticsAirlinesException {
        log.info("Adding flight: {}", flightDTORequest.toString());
        return ResponseEntity.ok(flightService.addFlight(flightDTORequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlightDTOResponse> updateFlight(@PathVariable Long id, @RequestBody FlightDTORequest flightDTORequest) throws GalaticsAirlinesException {
        log.info("Updating flight: {}", flightDTORequest.toString());
        return ResponseEntity.ok(flightService.updateFlight(id, flightDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            log.info("Flight Deleted");
            return ResponseEntity.accepted().build();
        } catch (GalaticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<FlightDTOResponse>> searchFlight (@RequestBody FilterFlightDTORequest filterFlightDTORequest) {
        log.info("Searching flight with filter: {}", filterFlightDTORequest.toString());
        return ResponseEntity.ok(flightService.searchFlight(filterFlightDTORequest));
    }
}
