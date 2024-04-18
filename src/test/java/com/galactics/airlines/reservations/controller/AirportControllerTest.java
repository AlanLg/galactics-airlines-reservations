package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.controller.AirportController;
import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.service.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAirportWhenGetAirportIsCalledWithValidId() throws GalaticsAirlinesException {
        Long id = 1L;
        AirportDTOResponse expectedResponse = new AirportDTOResponse();
        when(airportService.getAirport(id)).thenReturn(expectedResponse);

        ResponseEntity<AirportDTOResponse> response = airportController.getAirport(id);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(airportService, times(1)).getAirport(id);
    }

    @Test
    void shouldThrowExceptionWhenGetAirportIsCalledWithInvalidId() throws GalaticsAirlinesException {
        Long id = -1L;
        when(airportService.getAirport(id)).thenThrow(new GalaticsAirlinesException("Invalid id"));

        assertThrows(GalaticsAirlinesException.class, () -> airportController.getAirport(id));
        verify(airportService, times(1)).getAirport(id);
    }

    @Test
    void testAddAirport_Success() throws GalaticsAirlinesException {
        AirportDTORequest airportDTORequest = new AirportDTORequest();
        AirportDTOResponse expectedResponse = new AirportDTOResponse();
        when(airportService.addAirport(any())).thenReturn(expectedResponse);

        ResponseEntity<AirportDTOResponse> responseEntity = airportController.addAirport(airportDTORequest);

        assertNotNull(responseEntity);
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testUpdateAirport_Success() throws GalaticsAirlinesException {
        Long id = 1L;
        AirportDTORequest airportDTORequest = new AirportDTORequest();
        AirportDTOResponse expectedResponse = new AirportDTOResponse();
        when(airportService.updateAirport(eq(id), any())).thenReturn(expectedResponse);

        ResponseEntity<AirportDTOResponse> responseEntity = airportController.updateAirport(id, airportDTORequest);

        assertNotNull(responseEntity);
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testDeleteAirport_Success() throws GalaticsAirlinesException {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = airportController.deleteAirport(id);

        assertNotNull(responseEntity);
        assertEquals(202, responseEntity.getStatusCodeValue());
    }

    @Test
    void testDeleteAirport_Failure() throws GalaticsAirlinesException {
        Long id = 1L;
        doThrow(new GalaticsAirlinesException("Airport not found")).when(airportService).deleteAirport(id);

        ResponseEntity<Void> responseEntity = airportController.deleteAirport(id);

        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }
}
