package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.repository.AirplaneRepository;
import com.galactics.airlines.reservations.service.impl.AirplaneServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AirplaneServiceImplTest {
    @Mock
    private AirplaneRepository airplaneRepository;

    @InjectMocks
    private AirplaneServiceImpl airplaneService;

    @Test
    public void testAddAirplane_Success() throws GalaticsAirlinesException {
        AirplaneDTORequest airplaneDTORequest = new AirplaneDTORequest();
        airplaneDTORequest.setBrand("BrandTest");
        airplaneDTORequest.setModel("ModelTest");
        airplaneDTORequest.setManufacturingYear(2001);
        Airplane airplane = new Airplane();
        when(airplaneRepository.findByBrandAndModelAndManufacturingYear(any(), any(), any())).thenReturn(java.util.Optional.empty());
        when(airplaneRepository.save(any())).thenReturn(airplane);
        AirplaneDTOResponse response = airplaneService.addAirplane(airplaneDTORequest);

        assertNotNull(response);
    }

    @Test
    public void testUpdateAirplane_Success() throws GalaticsAirlinesException {
        Long id = 1L;
        AirplaneDTORequest airplaneDTORequest = new AirplaneDTORequest();
        airplaneDTORequest.setBrand("BrandTest");
        airplaneDTORequest.setModel("ModelTest");
        airplaneDTORequest.setManufacturingYear(2001);
        Airplane updatedAirplane = new Airplane();

        when(airplaneRepository.findById(anyLong())).thenReturn(Optional.of(updatedAirplane));
        when(airplaneRepository.save(any())).thenReturn(updatedAirplane);

        AirplaneDTOResponse response = airplaneService.updateAirplane(id, airplaneDTORequest);

        assertNotNull(response);
    }

    @Test
    public void testDeleteAirplane_Success() throws GalaticsAirlinesException {
        Long id = 1L;
        when(airplaneRepository.findById(id)).thenReturn(java.util.Optional.of(new Airplane()));
        airplaneService.deleteAirplane(id);
    }

    @Test
    public void testAddAirplane_NullRequest() {
        try {
            airplaneService.addAirplane(null);
            fail("Expected GalaticsAirlinesException was not thrown");
        } catch (GalaticsAirlinesException e) {
            assertEquals("Il manque un élément dans le JSON", e.getMessage());
        }
    }

    @Test
    public void testAddAirplane_InvalidData() {
        AirplaneDTORequest airplaneDTORequest = new AirplaneDTORequest();
        try {
            airplaneService.addAirplane(airplaneDTORequest);
            fail("Expected GalaticsAirlinesException was not thrown");
        } catch (GalaticsAirlinesException e) {
            assertEquals("Il manque un élément dans le JSON", e.getMessage());
        }
    }

    @Test
    public void testUpdateAirplane_NullId() {
        Long id = null;
        AirplaneDTORequest airplaneDTORequest = new AirplaneDTORequest();
        try {
            airplaneService.updateAirplane(id, airplaneDTORequest);
            fail("Expected GalaticsAirlinesException was not thrown");
        } catch (GalaticsAirlinesException e) {
            assertEquals("Aucun vol en bdd", e.getMessage());
        }
    }

    @Test
    public void testUpdateAirplane_NonExistingId() {
        Long id = 1L;
        AirplaneDTORequest airplaneDTORequest = new AirplaneDTORequest();
        when(airplaneRepository.findById(id)).thenReturn(java.util.Optional.empty());

        try {
            airplaneService.updateAirplane(id, airplaneDTORequest);
            fail("Expected GalaticsAirlinesException was not thrown");
        } catch (GalaticsAirlinesException e) {
            assertEquals("Aucun vol en bdd", e.getMessage());
        }
    }

    @Test
    public void testDeleteAirplane_NullId() {
        try {
            airplaneService.deleteAirplane(null);
            fail("Expected GalaticsAirlinesException was not thrown");
        } catch (GalaticsAirlinesException e) {
            assertEquals("Il manque un élément dans le JSON", e.getMessage());
        }
    }

    @Test
    public void testDeleteAirplane_NonExistingId() {
        Long id = 1L;
        when(airplaneRepository.findById(id)).thenReturn(java.util.Optional.empty());

        try {
            airplaneService.deleteAirplane(id);
            fail("Expected GalaticsAirlinesException was not thrown");
        } catch (GalaticsAirlinesException e) {
            assertEquals("Aucun vol en base", e.getMessage());
        }
    }

    @Test
    public void testFindOrSaveAirplane_AirplaneExists() {
        // Arrange
        Airplane airplane = new Airplane();
        airplane.setBrand("BrandTest");
        airplane.setModel("ModelTest");
        airplane.setManufacturingYear(2001);

        // Créer un avion fictif avec les mêmes caractéristiques
        Airplane existingAirplane = new Airplane();
        existingAirplane.setAirplaneId(1L);
        existingAirplane.setBrand("BrandTest");
        existingAirplane.setModel("ModelTest");
        existingAirplane.setManufacturingYear(2001);

        when(airplaneRepository.findByBrandAndModelAndManufacturingYear(any(), any(), any()))
                .thenReturn(Optional.of(existingAirplane));

        Airplane result = airplaneService.findOrSaveAirplane(airplane);

        assertNotNull(result);
        assertEquals(existingAirplane, result);

        verify(airplaneRepository).findByBrandAndModelAndManufacturingYear("BrandTest", "ModelTest", 2001);
        verify(airplaneRepository, never()).save(any());
    }

    @Test
    public void testFindOrSaveAirplane_AirplaneDoesNotExist() {
        // Arrange
        Airplane airplane = new Airplane();
        airplane.setBrand("Airbus");
        airplane.setModel("A320");
        airplane.setManufacturingYear(2019);

        when(airplaneRepository.findByBrandAndModelAndManufacturingYear(any(), any(), any()))
                .thenReturn(Optional.empty());

        when(airplaneRepository.save(any())).thenReturn(airplane);
        Airplane result = airplaneService.findOrSaveAirplane(airplane);

        // Assert
        assertNotNull(result);
        assertEquals(airplane, result);

        // Vérifier que findByBrandAndModelAndManufacturingYear a été appelée avec les bonnes valeurs
        verify(airplaneRepository).findByBrandAndModelAndManufacturingYear("Airbus", "A320", 2019);

        // Vérifier que save a été appelée, car l'avion n'existait pas
        verify(airplaneRepository).save(airplane);
    }
}
