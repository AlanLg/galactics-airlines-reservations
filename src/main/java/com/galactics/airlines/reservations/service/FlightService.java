package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.FilterFlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.model.entity.Flight;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface FlightService {
    FlightDTOResponse addFlight(FlightDTORequest flightDTOIn) throws GalacticsAirlinesException;

    FlightDTOResponse updateFlight(Long id, FlightDTORequest flightDTORequest);

    void deleteFlight(Long id);

    List<FlightDTOResponse> searchFlight(FilterFlightDTORequest filterFlightDTORequest);

    Flight getFlightForReservation(ReservationDTORequest reservationDTORequest);

    FlightDTOResponse getFlight(Long id) throws GalacticsAirlinesException;
}
