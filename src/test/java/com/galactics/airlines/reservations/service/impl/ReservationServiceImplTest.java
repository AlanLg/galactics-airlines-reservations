package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.entity.Client;
import com.galactics.airlines.reservations.model.entity.Flight;
import com.galactics.airlines.reservations.model.entity.Reservation;
import com.galactics.airlines.reservations.repository.ReservationRepository;
import com.galactics.airlines.reservations.service.ClientService;
import com.galactics.airlines.reservations.service.FlightService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    private final static EasyRandom generator = new EasyRandom();

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createReservationWithNoExistingClientSuccessfully() {
        ReservationDTORequestWithNoExistingClient request = generator.nextObject(ReservationDTORequestWithNoExistingClient.class);
        Client client = generator.nextObject(Client.class);
        Flight flight = generator.nextObject(Flight.class);

        when(clientService.createClientForReservation(request)).thenReturn(client);
        when(flightService.getFlightForReservation(request)).thenReturn(flight);
        when(reservationRepository.findByFlight_FlightIdAndClient_Id(anyLong(), anyLong())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> reservationService.createReservation(request));
    }

    @Test
    public void createReservationWithExistingClientSuccessfully() {
        ReservationDTORequestWithExistingClient request = generator.nextObject(ReservationDTORequestWithExistingClient.class);
        Client client = generator.nextObject(Client.class);
        Flight flight = generator.nextObject(Flight.class);

        when(clientService.getClientForReservation(request)).thenReturn(client);
        when(flightService.getFlightForReservation(request)).thenReturn(flight);
        when(reservationRepository.findByFlight_FlightIdAndClient_Id(anyLong(), anyLong())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> reservationService.createReservation(request));
    }
}