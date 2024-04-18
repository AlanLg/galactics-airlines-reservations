package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.FlightNotFoundException;
import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.FlightMapper;
import com.galactics.airlines.reservations.model.dto.request.FilterFlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.model.entity.Flight;
import com.galactics.airlines.reservations.repository.AirportRepository;
import com.galactics.airlines.reservations.repository.FlightRepository;
import com.galactics.airlines.reservations.service.FlightService;
import com.galactics.airlines.reservations.utils.FlightValidationUtils;
import com.galactics.airlines.reservations.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final AirplaneServiceImpl airplaneService;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightServiceImpl(AirplaneServiceImpl airplaneService, FlightRepository flightRepository, AirportRepository airportRepository) {
        this.airplaneService = airplaneService;
        this.flightRepository = flightRepository;
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

        if (!FlightValidationUtils.verifyElementInEntityToSave(updatedFlight)) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Airplane airplane = airplaneService.findOrSaveAirplane(updatedFlight.getAirplane());
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

    @Override
    public List<FlightDTOResponse> searchFlight(FilterFlightDTORequest filter) {
        List<Flight> flights = flightRepository.findAll();

        // Filtrer les vols en fonction des critères du filtre
        if (filter.getStartDate() != null && filter.getStartDate().isPresent() && ValidationUtils.isNotEmpty(String.valueOf(filter.getStartDate().get()))) {
            flights = filterByStartDate(flights, filter.getStartDate().get());
        }

        if (filter.getEndDate() != null && filter.getEndDate().isPresent() && ValidationUtils.isNotEmpty(String.valueOf(filter.getEndDate().get()))) {
            flights = filterByEndDate(flights, filter.getEndDate().get());
        }

        if (filter.getDepartureCity() != null && filter.getDepartureCity().isPresent() && ValidationUtils.isNotEmpty(filter.getDepartureCity().get())) {
            flights = filterByDepartureCity(flights, filter.getDepartureCity().get());
        }

        if (filter.getArrivalCity() != null && filter.getArrivalCity().isPresent() && ValidationUtils.isNotEmpty(filter.getArrivalCity().get())) {
            flights = filterByArrivalCity(flights, filter.getArrivalCity().get());
        }

        if (filter.getDepartureAirport() != null && filter.getDepartureAirport().isPresent() && ValidationUtils.isNotEmpty(filter.getDepartureAirport().get())) {
            flights = filterByDepartureAirport(flights, filter.getDepartureAirport().get());
        }

        if (filter.getArrivalAirport() != null && filter.getArrivalAirport().isPresent() && ValidationUtils.isNotEmpty(filter.getArrivalAirport().get())) {
            flights = filterByArrivalAirport(flights, filter.getArrivalAirport().get());
        }

        if (filter.getAirplaneModel() != null && filter.getAirplaneModel().isPresent() && ValidationUtils.isNotEmpty(filter.getAirplaneModel().get())) {
            flights = filterByAirplaneModel(flights, filter.getAirplaneModel().get());
        }

        return FlightMapper.INSTANCE.flightEntitiesToFlightDTOResponses(flights);
    }

    @Override
    public Flight getFlightForReservation(ReservationDTORequest reservationDTORequest) {
        return flightRepository.findByDepartureCityAndArrivalCityAndDepartureDateTimeAndArrivalDateTime(
                reservationDTORequest.getDepartureCity(),
                reservationDTORequest.getArrivalCity(),
                reservationDTORequest.getDepartureDateTime(),
                reservationDTORequest.getArrivalDateTime()
        ).orElseThrow(FlightNotFoundException::new);
    }

    private List<Flight> filterByStartDate(List<Flight> flights, LocalDateTime startDate) {
        return flights.stream()
                .filter(flight -> flight.getDepartureDateTime().isEqual(startDate))
                .toList();
    }

    private List<Flight> filterByEndDate(List<Flight> flights, LocalDateTime endDate) {
        return flights.stream()
                .filter(flight -> flight.getArrivalDateTime().isEqual(endDate))
                .toList();
    }

    private List<Flight> filterByDepartureCity(List<Flight> flights, String departureCity) {
        return flights.stream()
                .filter(flight -> flight.getDepartureCity().equalsIgnoreCase(departureCity))
                .toList();
    }

    private List<Flight> filterByArrivalCity(List<Flight> flights, String arrivalCity) {
        return flights.stream()
                .filter(flight -> flight.getArrivalCity().equalsIgnoreCase(arrivalCity))
                .toList();
    }

    private List<Flight> filterByDepartureAirport(List<Flight> flights, String departureAirport) {
        return flights.stream()
                .filter(flight -> flight.getDepartureAirport().getAirportName().equalsIgnoreCase(departureAirport))
                .toList();
    }

    private List<Flight> filterByArrivalAirport(List<Flight> flights, String arrivalAirport) {
        return flights.stream()
                .filter(flight -> flight.getArrivalAirport().getAirportName().equalsIgnoreCase(arrivalAirport))
                .toList();
    }

    private List<Flight> filterByAirplaneModel(List<Flight> flights, String airplaneModel) {
        return flights.stream()
                .filter(flight -> flight.getAirplane().getModel().equalsIgnoreCase(airplaneModel))
                .toList();
    }


    private Airport findOrSaveAirport(Airport airport) {
        return airportRepository.findByAirportNameAndCountryAndCity(
                airport.getAirportName(),
                airport.getCountry(),
                airport.getCity()
        ).orElseGet(() -> airportRepository.save(airport));
    }
}

