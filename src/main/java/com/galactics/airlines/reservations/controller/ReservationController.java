package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.model.dto.response.ReservationDTOResponse;
import com.galactics.airlines.reservations.service.ReservationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation")
@OpenAPIDefinition(info = @Info(title = "Galatics Airlines API", version = "v1"))
@SecurityRequirement(name = "basicAuth")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<ReservationDTOResponse> addFlight(@RequestBody ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient) throws GalaticsAirlinesException {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTORequestWithNoExistingClient));
    }
}
