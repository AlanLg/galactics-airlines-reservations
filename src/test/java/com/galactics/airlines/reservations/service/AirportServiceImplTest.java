package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.AirportMapper;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.repository.AirportRepository;
import com.galactics.airlines.reservations.service.impl.AirportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AirportServiceImplTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportServiceImpl airportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAirport_Success() throws GalaticsAirlinesException {
        // Arrange
        AirportDTORequest airportDTORequest = new AirportDTORequest();
        airportDTORequest.setAirportName("CDG");
        airportDTORequest.setCountry("France");
        airportDTORequest.setCity("Paris");
        Airport createdAirport = new Airport();
        AirportDTOResponse expectedResponse = new AirportDTOResponse();
        when(airportRepository.findByAirportNameAndCountryAndCity(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(airportRepository.save(any())).thenReturn(createdAirport);

        // Act
        AirportDTOResponse response = airportService.addAirport(airportDTORequest);

        // Assert
        assertNotNull(response);
    }

    @Test
    void testUpdateAirport_Success() throws GalaticsAirlinesException {
        // Arrange
        Long id = 1L;
        AirportDTORequest airportDTORequest = new AirportDTORequest();
        airportDTORequest.setAirportName("CDG");
        airportDTORequest.setCountry("France");
        airportDTORequest.setCity("Paris");
        Airport updatedAirport = new Airport();
        updatedAirport.setAirportId(id);
        when(airportRepository.findById(id)).thenReturn(Optional.of(updatedAirport));
        when(airportRepository.save(any())).thenReturn(updatedAirport);

        // Act
        AirportDTOResponse response = airportService.updateAirport(id, airportDTORequest);

        // Assert
        assertNotNull(response);
        assertEquals(id, response.getAirportId());
    }

    @Test
    void testDeleteAirport_Success() throws GalaticsAirlinesException {
        // Arrange
        Long id = 1L;
        Airport airport = new Airport();
        when(airportRepository.findById(id)).thenReturn(Optional.of(airport));

        // Act
        airportService.deleteAirport(id);

        // Assert
        verify(airportRepository, times(1)).delete(airport);
    }

    @Test
    void testDeleteAirport_Failure_IdNull() {
        // Act & Assert
        assertThrows(GalaticsAirlinesException.class, () -> airportService.deleteAirport(null));
    }

    @Test
    void testDeleteAirport_Failure_AirportNotFound() {
        // Arrange
        Long id = 1L;
        when(airportRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GalaticsAirlinesException.class, () -> airportService.deleteAirport(id));
    }
}
