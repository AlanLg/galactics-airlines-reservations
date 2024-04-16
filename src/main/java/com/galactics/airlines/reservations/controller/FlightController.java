package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.model.dto.request.FilterFlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<FlightDTOResponse> addFlight(@RequestBody FlightDTORequest flightDTORequest) throws GalaticsAirlinesException {
        return ResponseEntity.ok(flightService.addFlight(flightDTORequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlightDTOResponse> updateFlight(@PathVariable Long id, @RequestBody FlightDTORequest flightDTORequest) throws GalaticsAirlinesException {
        return ResponseEntity.ok(flightService.updateFlight(id, flightDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.accepted().build();
        } catch (GalaticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDTOResponse>> searchFlight (@RequestBody FilterFlightDTORequest filterFlightDTORequest) {
        return ResponseEntity.ok(flightService.searchFlight(filterFlightDTORequest));
    }
}
