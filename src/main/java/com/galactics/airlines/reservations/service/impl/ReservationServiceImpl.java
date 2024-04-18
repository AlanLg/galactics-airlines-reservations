package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.ClientMapper;
import com.galactics.airlines.reservations.mapper.ReservationMapper;
import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.ClientDTOResponse;
import com.galactics.airlines.reservations.model.dto.response.ReservationDTOResponse;
import com.galactics.airlines.reservations.model.entity.Client;
import com.galactics.airlines.reservations.model.entity.Flight;
import com.galactics.airlines.reservations.model.entity.Reservation;
import com.galactics.airlines.reservations.repository.FlightRepository;
import com.galactics.airlines.reservations.repository.ReservationRepository;
import com.galactics.airlines.reservations.service.ClientService;
import com.galactics.airlines.reservations.service.FlightService;
import com.galactics.airlines.reservations.service.ReservationService;
import com.galactics.airlines.reservations.utils.ReservationValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientService clientService;
    private final FlightService flightService;

    @Override
    public ReservationDTOResponse createReservation(ReservationDTORequestWithNoExistingClient reservationDTORequest) {
        Client client = clientService.createClientForReservation(reservationDTORequest);
        return finaliserReservation(reservationDTORequest, client);
    }

    @Override
    public ReservationDTOResponse createReservation(ReservationDTORequestWithExistingClient reservationDTORequest) {
        log.info("Création d'une réservation avec un client existant");
        Client client = clientService.getClientForReservation(reservationDTORequest);
        log.info("Client trouvé : {}", client);
        return finaliserReservation(reservationDTORequest, client);
    }

    private ReservationDTOResponse finaliserReservation(ReservationDTORequest reservationDTORequest, Client client) {
        log.info("Chercher un vol");
        Flight flight = flightService.getFlightForReservation(reservationDTORequest);
        log.info("vol trouve");

        Reservation createdReservation = ReservationMapper.INSTANCE.clientEntityAndFlightEntityToReservationEntity(client, flight);
        log.info("mapping de la réservation");

        if (!ReservationValidationUtils.isValidReservation(createdReservation)) {
            throw new GalaticsAirlinesException("Il manque un élément dans la réservation");
        }

        Optional<Reservation> existingReservation = reservationRepository
                .findByFlight_FlightIdAndClient_Id(createdReservation.getFlight().getFlightId(), createdReservation.getClient().getId());
        log.info("recherche de la réservation existante");

        if (existingReservation.isPresent()) {
            log.info("trouve un reservation existante");
            return ReservationMapper.INSTANCE.reservationEntityToReservationDTOResponse(existingReservation.get());
        }

        log.info("sauvegarde de la reservation creee");
        Reservation reservation = reservationRepository.save(createdReservation);

        return ReservationMapper.INSTANCE.reservationEntityToReservationDTOResponse(reservation);
    }
}
