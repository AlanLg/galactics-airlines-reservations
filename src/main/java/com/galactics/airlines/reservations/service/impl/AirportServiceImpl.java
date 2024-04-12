package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.AirplaneMapper;
import com.galactics.airlines.reservations.mapper.AirportMapper;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.repository.AirportRepository;
import com.galactics.airlines.reservations.service.AirportService;
import com.galactics.airlines.reservations.service.utils.AirplaneValidationUtils;
import com.galactics.airlines.reservations.service.utils.AirportValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public AirportDTOResponse addAirport(AirportDTORequest airportDTORequest) throws GalaticsAirlinesException {
        Airport createdAirport = AirportMapper.INSTANCE.airportDTORequestToAirportEntity(airportDTORequest);

        if (!AirportValidationUtils.isValidAirport(createdAirport)) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Optional<Airport> existingAirport = airportRepository.findByAirportNameAndCountryAndCity(createdAirport.getAirportName(), createdAirport.getCountry(), createdAirport.getCity());

        if (existingAirport.isPresent()) {
            return AirportMapper.INSTANCE.airportEntityToAirportDTORequest(existingAirport.get());
        }

        return AirportMapper.INSTANCE.airportEntityToAirportDTORequest(airportRepository.save(createdAirport));
    }
}
