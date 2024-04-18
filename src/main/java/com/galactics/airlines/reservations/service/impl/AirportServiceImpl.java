package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.mapper.AirportMapper;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.repository.AirportRepository;
import com.galactics.airlines.reservations.service.AirportService;
import com.galactics.airlines.reservations.utils.AirportValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public AirportDTOResponse getAirport(Long id) throws GalacticsAirlinesException {
        if (id == null) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        Airport targetAirport = airportRepository.findById(id).orElse(null);

        if (targetAirport == null) {
            throw new GalacticsAirlinesException("No flight in the data base");
        }
        return AirportMapper.INSTANCE.airportEntityToAirportDTOResponse(targetAirport);
    }

    @Override
    public AirportDTOResponse addAirport(AirportDTORequest airportDTORequest) throws GalacticsAirlinesException {
        Airport createdAirport = AirportMapper.INSTANCE.airportDTORequestToAirportEntity(airportDTORequest);

        if (!AirportValidationUtils.isValidAirport(createdAirport)) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        Optional<Airport> existingAirport = airportRepository.findByAirportNameAndCountryAndCity(createdAirport.getAirportName(), createdAirport.getCountry(), createdAirport.getCity());

        if (existingAirport.isPresent()) {
            return AirportMapper.INSTANCE.airportEntityToAirportDTOResponse(existingAirport.get());
        }

        return AirportMapper.INSTANCE.airportEntityToAirportDTOResponse(airportRepository.save(createdAirport));
    }

    @Override
    public AirportDTOResponse updateAirport(Long id, AirportDTORequest airportDTORequest) throws GalacticsAirlinesException {
        if (id == null || airportRepository.findById(id).isEmpty()) {
            throw new GalacticsAirlinesException("Aucun airport en bdd");
        }

        Airport updatedAirport = AirportMapper.INSTANCE.airportDTORequestToAirportEntity(airportDTORequest);

        if (!AirportValidationUtils.isValidAirport(updatedAirport)) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        updatedAirport.setAirportId(id);
        airportRepository.save(updatedAirport);
        return AirportMapper.INSTANCE.airportEntityToAirportDTOResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) throws GalacticsAirlinesException {
        if (id == null) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        Airport airport = airportRepository.findById(id).orElse(null);
        if (airport != null) {
            airportRepository.delete(airport);
        } else {
            throw new GalacticsAirlinesException("Aucun aeroport en base");
        }
    }

    public Airport findOrSaveAirport(Airport airport) {
        return airportRepository.findByAirportNameAndCountryAndCity(
                airport.getAirportName(),
                airport.getCountry(),
                airport.getCity()
        ).orElseGet(() -> airportRepository.save(airport));
    }
}
