package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface AirportService {

    AirportDTOResponse addAirport(AirportDTORequest airportDTORequest) throws GalaticsAirlinesException;
}
