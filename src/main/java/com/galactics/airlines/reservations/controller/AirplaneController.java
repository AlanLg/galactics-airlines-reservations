package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.service.AirplaneService;
import com.galactics.airlines.reservations.service.FlightService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/airplane")
@OpenAPIDefinition(info = @Info(title = "Galatics Airlines API", version = "v1"))
@SecurityRequirement(name = "basicAuth")
public class AirplaneController {
    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping("/add")
    public ResponseEntity<AirplaneDTOResponse> addFlight(@RequestBody AirplaneDTORequest airplaneDTORequest) throws GalaticsAirlinesException {
        log.info("Adding airplane: {}", airplaneDTORequest.toString());
        return ResponseEntity.ok(airplaneService.addAirplane(airplaneDTORequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AirplaneDTOResponse> updateFlight(@PathVariable Long id, @RequestBody AirplaneDTORequest airplaneDTORequest) throws GalaticsAirlinesException {
        log.info("Updating airplane: {}", airplaneDTORequest.toString());
        return ResponseEntity.ok(airplaneService.updateAirplane(id, airplaneDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        try {
            airplaneService.deleteAirplane(id);
            log.info("Airplane deleted");
            return ResponseEntity.accepted().build();
        } catch (GalaticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
