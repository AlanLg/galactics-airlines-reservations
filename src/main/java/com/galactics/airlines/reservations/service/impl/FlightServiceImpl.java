package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.FlightMapper;
import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.model.entity.Flight;
import com.galactics.airlines.reservations.repository.AirplaneRepository;
import com.galactics.airlines.reservations.repository.AirportRepository;
import com.galactics.airlines.reservations.repository.FlightRepository;
import com.galactics.airlines.reservations.service.FlightService;
import com.galactics.airlines.reservations.service.utils.FlightValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirportRepository airportRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirplaneRepository airplaneRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public FlightDTOResponse addFlight(FlightDTORequest flightDTORequest) throws GalaticsAirlinesException {
        Flight createdFlight = linkAndSaveAssociatedEntities(flightDTORequest);

        Optional<Flight> existingFlight = flightRepository.findByDepartureCityAndArrivalCityAndDepartureAirportAndArrivalAirportAndAirplane(
                createdFlight.getDepartureCity(),
                createdFlight.getArrivalCity(),
                createdFlight.getDepartureAirport(),
                createdFlight.getArrivalAirport(),
                createdFlight.getAirplane()
        );

        if (existingFlight.isPresent()) {
            return FlightMapper.INSTANCE.flightEntityToFlightDTOResponse(existingFlight.get());
        }

        return FlightMapper.INSTANCE.flightEntityToFlightDTOResponse(flightRepository.save(createdFlight));
    }

    @Override
    public FlightDTOResponse updateFlight(Long id, FlightDTORequest flightDTORequest) throws GalaticsAirlinesException {
        if (id == null || flightRepository.findById(id).isEmpty()) {
            throw new GalaticsAirlinesException("Aucun vol en bdd");
        }

        Flight updatedFlight = linkAndSaveAssociatedEntities(flightDTORequest);;
        updatedFlight.setFlightId(id);
        flightRepository.save(updatedFlight);
        return FlightMapper.INSTANCE.flightEntityToFlightDTOResponse(updatedFlight);
    }

    private Flight linkAndSaveAssociatedEntities(FlightDTORequest flightDTORequest) {
        Flight updatedFlight = FlightMapper.INSTANCE.flightDTORequestToFlightEntity(flightDTORequest);

        if (FlightValidationUtils.verifyElementInEntityToSave(updatedFlight)) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Airplane airplane = findOrSaveAirplane(updatedFlight.getAirplane());
        updatedFlight.setAirplane(airplane);

        Airport departureAirport = findOrSaveAirport(updatedFlight.getDepartureAirport());
        updatedFlight.setDepartureAirport(departureAirport);

        Airport arrivalAirport = findOrSaveAirport(updatedFlight.getArrivalAirport());
        updatedFlight.setArrivalAirport(arrivalAirport);

        return updatedFlight;
    }

    @Override
    public void deleteFlight(Long id) {
        if (id == null) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            flightRepository.delete(flight);
        } else {
            throw new GalaticsAirlinesException("Aucun vol en base");
        }
    }

    private Airplane findOrSaveAirplane(Airplane airplane) {
        return airplaneRepository.findByBrandAndModelAndManufacturingYear(
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturingYear()
        ).orElseGet(() -> airplaneRepository.save(airplane));
    }



    private Airport findOrSaveAirport(Airport airport) {
        return airportRepository.findByAirportNameAndCountryAndCity(
                airport.getAirportName(),
                airport.getCountry(),
                airport.getCity()
        ).orElseGet(() -> airportRepository.save(airport));
    }
}

