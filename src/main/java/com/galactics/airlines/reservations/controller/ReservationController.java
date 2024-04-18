package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
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

    @PostMapping("/add/without-existing-client")
    public ResponseEntity<ReservationDTOResponse> addReservationWithNoExistingClient(@RequestBody ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient) throws GalaticsAirlinesException {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTORequestWithNoExistingClient));
    }

    @PostMapping("/add/with-existing-client")
    public ResponseEntity<ReservationDTOResponse> addReservationWithExistingClient(@RequestBody ReservationDTORequestWithExistingClient reservationDTORequestWithExistingClient) throws GalaticsAirlinesException {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTORequestWithExistingClient));
    }
}
