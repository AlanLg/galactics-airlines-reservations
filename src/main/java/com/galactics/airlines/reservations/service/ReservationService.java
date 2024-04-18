package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.ReservationDTOResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    ReservationDTOResponse createReservation(ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient);
    ReservationDTOResponse createReservation(ReservationDTORequestWithExistingClient reservationDTORequestWithExistingClient);
}
