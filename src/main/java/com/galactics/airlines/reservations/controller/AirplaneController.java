package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.service.AirplaneService;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<AirplaneDTOResponse> getAirplane(@PathVariable Long id) throws GalacticsAirlinesException {
        return ResponseEntity.ok(airplaneService.getAirplane(id));
    }

    @PostMapping("/add")
    public ResponseEntity<AirplaneDTOResponse> addAirplane(@RequestBody AirplaneDTORequest airplaneDTORequest) throws GalacticsAirlinesException {
        log.info("Adding airplane: {}", airplaneDTORequest.toString());
        return ResponseEntity.ok(airplaneService.addAirplane(airplaneDTORequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AirplaneDTOResponse> updateAirplane(@PathVariable Long id, @RequestBody AirplaneDTORequest airplaneDTORequest) throws GalacticsAirlinesException {
        log.info("Updating airplane: {}", airplaneDTORequest.toString());
        return ResponseEntity.ok(airplaneService.updateAirplane(id, airplaneDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAirplane(@PathVariable Long id) {
        try {
            airplaneService.deleteAirplane(id);
            log.info("Airplane deleted");
            return ResponseEntity.accepted().build();
        } catch (GalacticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
