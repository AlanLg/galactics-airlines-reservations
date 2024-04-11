package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
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
    void testAddFlight_Success() throws GalaticsAirlinesException {
        AirportDTORequest airport = new AirportDTORequest();
        airport.setAirportId(1L);
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
        request.setDepartureDateTime("2024-04-12T08:00:00");
        request.setArrivalDateTime("2024-04-12T08:00:00");
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

        // Act
        FlightDTOResponse response = flightService.addFlight(request);

        // Assert
        assertNotNull(response);
    }

    @Test
    void testUpdateFlight_Success() throws GalaticsAirlinesException {
        // Arrange
        Long id = 1L;
        AirportDTORequest airport = new AirportDTORequest();
        airport.setAirportId(1L);
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
        request.setDepartureDateTime("2024-04-12T08:00:00");
        request.setArrivalDateTime("2024-04-12T08:00:00");
        request.setNumberOfSeats(500);
        request.setDepartureAirport(airport);
        request.setArrivalAirport(airport);
        request.setAirplane(airplane);

        Flight flight = new Flight();

        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any())).thenReturn(flight);
        when(airplaneService.findOrSaveAirplane(any())).thenReturn(new Airplane());
        when(airportRepository.findByAirportNameAndCountryAndCity(any(), any(), any())).thenReturn(Optional.of(new Airport()));

        // Act
        FlightDTOResponse response = flightService.updateFlight(id, request);

        // Assert
        assertNotNull(response);
    }

    @Test
    void testDeleteFlight_Success() throws GalaticsAirlinesException {
        // Arrange
        Long id = 1L;
        Flight flight = new Flight();
        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));

        // Act
        flightService.deleteFlight(id);

        // Assert
        verify(flightRepository, times(1)).delete(flight);
    }

    @Test
    void testDeleteFlight_Failure_IdNull() {
        // Act & Assert
        assertThrows(GalaticsAirlinesException.class, () -> flightService.deleteFlight(null));
    }

    @Test
    void testDeleteFlight_Failure_FlightNotFound() {
        // Arrange
        Long id = 1L;
        when(flightRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GalaticsAirlinesException.class, () -> flightService.deleteFlight(id));
    }
}
