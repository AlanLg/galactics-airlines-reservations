package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.ClientMapper;
import com.galactics.airlines.reservations.mapper.ReservationMapper;
import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientService clientService;
    private final FlightService flightService;

    @Override
    public ReservationDTOResponse createReservation(ReservationDTORequestWithNoExistingClient reservationDTORequest) {
        Client client = clientService.createClientForReservation(reservationDTORequest);
        Flight flight = flightService.getFlightForReservation(reservationDTORequest);

        Reservation createdReservation = ReservationMapper.INSTANCE.clientEntityAndFlightEntityToReservationEntity(client, flight);

        if (!ReservationValidationUtils.isValidReservation(createdReservation)) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Optional<Reservation> existingReservation = reservationRepository
                .findByFlight_FlightIdAndClient_Id(createdReservation.getFlight().getFlightId(), createdReservation.getClient().getId());

        if (existingReservation.isPresent()) {
            return ReservationMapper.INSTANCE.reservationEntityToReservationDTOResponse(existingReservation.get());
        }

        Reservation reservation = reservationRepository.save(createdReservation);

        return ReservationMapper.INSTANCE.reservationEntityToReservationDTOResponse(reservation);
    }
}
