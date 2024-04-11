package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface AirplaneService {
    AirplaneDTOResponse addAirplane(AirplaneDTORequest airplaneDTORequest) throws GalaticsAirlinesException;
    AirplaneDTOResponse updateAirplane(Long id, AirplaneDTORequest airplaneDTORequest);
    void deleteAirplane(Long id);
}