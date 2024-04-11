package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

@Transactional
public interface FlightService {
    FlightDTOResponse addFlight(FlightDTORequest flightDTOIn) throws GalaticsAirlinesException;

    FlightDTOResponse updateFlight(Long id, FlightDTORequest flightDTORequest);

    void deleteFlight(Long id);
}
