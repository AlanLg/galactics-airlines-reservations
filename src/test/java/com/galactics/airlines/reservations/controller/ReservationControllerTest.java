package com.galactics.airlines.reservations.controller;
import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.ReservationDTOResponse;
import com.galactics.airlines.reservations.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addReservationWithNoExistingClientReturnsReservationResponseSuccessfully() throws GalacticsAirlinesException {
        ReservationDTORequestWithNoExistingClient request = new ReservationDTORequestWithNoExistingClient();
        ReservationDTOResponse expectedResponse = new ReservationDTOResponse();
        when(reservationService.createReservation(request)).thenReturn(expectedResponse);

        ResponseEntity<ReservationDTOResponse> response = reservationController.addReservationWithNoExistingClient(request);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(reservationService, times(1)).createReservation(request);
    }

    @Test
    void addReservationWithExistingClientReturnsReservationResponseSuccessfully() throws GalacticsAirlinesException {
        ReservationDTORequestWithExistingClient request = new ReservationDTORequestWithExistingClient();
        ReservationDTOResponse expectedResponse = new ReservationDTOResponse();
        when(reservationService.createReservation(request)).thenReturn(expectedResponse);

        ResponseEntity<ReservationDTOResponse> response = reservationController.addReservationWithExistingClient(request);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(reservationService, times(1)).createReservation(request);
    }
}