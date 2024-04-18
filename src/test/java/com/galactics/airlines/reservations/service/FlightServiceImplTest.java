package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.FlightMapper;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FilterFlightDTORequest;
import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.model.entity.Flight;
import com.galactics.airlines.reservations.repository.AirportRepository;
import com.galactics.airlines.reservations.repository.FlightRepository;
import com.galactics.airlines.reservations.service.impl.AirplaneServiceImpl;
import com.galactics.airlines.reservations.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FlightServiceImplTest {

    @Mock
    private AirplaneServiceImpl airplaneService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnFlightWhenGetFlightIsCalledWithValidId() throws GalaticsAirlinesException {
        Long id = 1L;
        Flight flight = new Flight();
        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));
        FlightDTOResponse expectedResponse = FlightMapper.INSTANCE.flightEntityToFlightDTOResponse(flight);

        FlightDTOResponse response = flightService.getFlight(id);

        assertEquals(expectedResponse, response);
        verify(flightRepository, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenGetFlightIsCalledWithNullId() {
        assertThrows(GalaticsAirlinesException.class, () -> flightService.getFlight(null));
        verify(flightRepository, never()).findById(any());
    }

    @Test
    void shouldThrowExceptionWhenGetFlightIsCalledWithNonExistingId() {
        Long id = -1L;
        when(flightRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(GalaticsAirlinesException.class, () -> flightService.getFlight(id));
        verify(flightRepository, times(1)).findById(id);
    }

    @Test
    void testAddFlight_Success() throws GalaticsAirlinesException {
        AirportDTORequest airport = new AirportDTORequest();
        airport.setAirportName("TestAirportName");
        airport.setCity("TestCity");
        airport.setCountry("TestCountry");

        AirplaneDTORequest airplane = new AirplaneDTORequest();
        airplane.setBrand("BrandTest");
        airplane.setModel("ModelTest");
        airplane.setManufacturingYear(2001);

        FlightDTORequest request = new FlightDTORequest();
        request.setDepartureCity("TestCity");
        request.setArrivalCity("TestCity");
        request.setDepartureDateTime(LocalDateTime.now());
        request.setArrivalDateTime(LocalDateTime.now());
        request.setNumberOfSeats(500);
        request.setDepartureAirport(airport);
        request.setArrivalAirport(airport);
        request.setAirplane(airplane);

        Flight flight = new Flight();

        when(flightRepository.findByDepartureCityAndArrivalCityAndDepartureAirportAndArrivalAirportAndAirplane(
                any(), any(), any(), any(), any())).thenReturn(Optional.empty());
        when(flightRepository.save(any())).thenReturn(flight);
        when(airplaneService.findOrSaveAirplane(any())).thenReturn(new Airplane());
        when(airportRepository.findByAirportNameAndCountryAndCity(any(), any(), any())).thenReturn(Optional.of(new Airport()));

        FlightDTOResponse response = flightService.addFlight(request);

        assertNotNull(response);
    }

    @Test
    void testUpdateFlight_Success() throws GalaticsAirlinesException {
        Long id = 1L;
        AirportDTORequest airport = new AirportDTORequest();
        airport.setAirportName("TestAirportName");
        airport.setCity("TestCity");
        airport.setCountry("TestCountry");

        AirplaneDTORequest airplane = new AirplaneDTORequest();
        airplane.setBrand("BrandTest");
        airplane.setModel("ModelTest");
        airplane.setManufacturingYear(2001);

        FlightDTORequest request = new FlightDTORequest();
        request.setDepartureCity("TestCity");
        request.setArrivalCity("TestCity");
        request.setDepartureDateTime(LocalDateTime.now());
        request.setArrivalDateTime(LocalDateTime.now());
        request.setNumberOfSeats(500);
        request.setDepartureAirport(airport);
        request.setArrivalAirport(airport);
        request.setAirplane(airplane);

        Flight flight = new Flight();

        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any())).thenReturn(flight);
        when(airplaneService.findOrSaveAirplane(any())).thenReturn(new Airplane());
        when(airportRepository.findByAirportNameAndCountryAndCity(any(), any(), any())).thenReturn(Optional.of(new Airport()));

        FlightDTOResponse response = flightService.updateFlight(id, request);

        assertNotNull(response);
    }

    @Test
    void testDeleteFlight_Success() throws GalaticsAirlinesException {
        Long id = 1L;
        Flight flight = new Flight();
        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));

        flightService.deleteFlight(id);

        verify(flightRepository, times(1)).delete(flight);
    }

    @Test
    void testDeleteFlight_Failure_IdNull() {
        assertThrows(GalaticsAirlinesException.class, () -> flightService.deleteFlight(null));
    }

    @Test
    void testDeleteFlight_Failure_FlightNotFound() {
        Long id = 1L;
        when(flightRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(GalaticsAirlinesException.class, () -> flightService.deleteFlight(id));
    }

    @Test
    void testSearchFlight_NoFilter() throws GalaticsAirlinesException {
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        List<Flight> flights = Arrays.asList(new Flight(), new Flight(), new Flight());
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithStartDateFilter() throws GalaticsAirlinesException {
        Flight flight1 = new Flight();
        flight1.setDepartureDateTime(LocalDateTime.of(2024, 1, 1, 1, 1));
        Flight flight2 = new Flight();
        flight2.setDepartureDateTime(LocalDateTime.of(2024, 1, 1, 1, 1));
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setStartDate(Optional.of(LocalDateTime.of(2024, 1, 1, 1, 1)));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);


        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithEndDateFilter() throws GalaticsAirlinesException {
        Flight flight1 = new Flight();
        flight1.setArrivalDateTime(LocalDateTime.of(2024, 1, 1, 1, 1));
        Flight flight2 = new Flight();
        flight2.setArrivalDateTime(LocalDateTime.of(2024, 1, 1, 1, 1));
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setEndDate(Optional.of(LocalDateTime.of(2024, 1, 1, 1, 1)));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithDepartureCityFilter() throws GalaticsAirlinesException {
        Flight flight1 = new Flight();
        flight1.setDepartureCity("New York");
        Flight flight2 = new Flight();
        flight2.setDepartureCity("New York");
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setDepartureCity(Optional.of("New York"));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithArrivalCityFilter() throws GalaticsAirlinesException {
        Flight flight1 = new Flight();
        flight1.setArrivalCity("New York");
        Flight flight2 = new Flight();
        flight2.setArrivalCity("New York");
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setArrivalCity(Optional.of("New York"));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithDepartureAirportFilter() throws GalaticsAirlinesException {
        Airport airport1 = new Airport();
        airport1.setAirportName("CDG");
        Flight flight1 = new Flight();
        flight1.setDepartureAirport(airport1);
        Flight flight2 = new Flight();
        flight2.setDepartureAirport(airport1);
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setDepartureAirport(Optional.of("CDG"));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithArrivalAirportFilter() throws GalaticsAirlinesException {
        Airport airport1 = new Airport();
        airport1.setAirportName("CDG");
        Flight flight1 = new Flight();
        flight1.setArrivalAirport(airport1);
        Flight flight2 = new Flight();
        flight2.setArrivalAirport(airport1);
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setArrivalAirport(Optional.of("CDG"));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }

    @Test
    void testSearchFlight_WithAirplaneModelFilter() throws GalaticsAirlinesException {
        Airplane airplane = new Airplane();
        airplane.setBrand("FB2001");
        Flight flight1 = new Flight();
        flight1.setAirplane(airplane);
        Flight flight2 = new Flight();
        flight2.setAirplane(airplane);
        FilterFlightDTORequest filter = new FilterFlightDTORequest();
        filter.setAirplaneBrand(Optional.of("FB2001"));
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDTOResponse> response = flightService.searchFlight(filter);

        assertNotNull(response);
        assertEquals(flights.size(), response.size());
    }
}
