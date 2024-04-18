package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.ClientDTOResponse;
import com.galactics.airlines.reservations.model.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    ClientDTOResponse addClient(ClientDTORequest clientDTORequest);
    ClientDTOResponse updateClient(Long id, ClientDTORequest clientDTORequest);
    void deleteClient(Long id);
    Client createClientForReservation(ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient);
    Client getClientForReservation(ReservationDTORequestWithExistingClient reservationDTORequestWithExistingClient);
}
