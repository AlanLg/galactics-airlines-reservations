package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.service.AirplaneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AirplaneControllerTest {

    @Mock
    private AirplaneService airplaneService;

    @InjectMocks
    private AirplaneController airplaneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAirplane_Success() throws GalaticsAirlinesException {
        AirplaneDTORequest request = new AirplaneDTORequest();
        AirplaneDTOResponse expectedResponse = new AirplaneDTOResponse();
        when(airplaneService.addAirplane(request)).thenReturn(expectedResponse);

        ResponseEntity<AirplaneDTOResponse> response = airplaneController.addFlight(request);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(airplaneService, times(1)).addAirplane(request);
    }

    @Test
    void testUpdateAirplane_Success() throws GalaticsAirlinesException {
        Long id = 1L;
        AirplaneDTORequest request = new AirplaneDTORequest();
        AirplaneDTOResponse expectedResponse = new AirplaneDTOResponse();
        when(airplaneService.updateAirplane(id, request)).thenReturn(expectedResponse);

        ResponseEntity<AirplaneDTOResponse> response = airplaneController.updateFlight(id, request);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        verify(airplaneService, times(1)).updateAirplane(id, request);
    }

    @Test
    void testDeleteAirplane_Success() throws GalaticsAirlinesException {
        Long id = 1L;

        ResponseEntity<Void> response = airplaneController.deleteFlight(id);

        assertEquals(202, response.getStatusCodeValue());

        verify(airplaneService, times(1)).deleteAirplane(id);
    }

    @Test
    void testDeleteAirplane_Failure() throws GalaticsAirlinesException {
        Long id = 1L;
        doThrow(new GalaticsAirlinesException("")).when(airplaneService).deleteAirplane(id);

        ResponseEntity<Void> response = airplaneController.deleteFlight(id);

        assertEquals(400, response.getStatusCodeValue());

        verify(airplaneService, times(1)).deleteAirplane(id);
    }
}
