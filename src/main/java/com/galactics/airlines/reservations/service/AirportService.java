package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface AirportService {
    AirportDTOResponse getAirport(Long id) throws GalacticsAirlinesException;
    AirportDTOResponse addAirport(AirportDTORequest airportDTORequest) throws GalacticsAirlinesException;
    AirportDTOResponse updateAirport(Long id, AirportDTORequest airportDTORequest) throws GalacticsAirlinesException;
    void deleteAirport(Long id) throws GalacticsAirlinesException;
}
