package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.mapper.AirplaneMapper;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.repository.AirplaneRepository;
import com.galactics.airlines.reservations.service.AirplaneService;
import com.galactics.airlines.reservations.utils.AirplaneValidationUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public AirplaneDTOResponse addAirplane(AirplaneDTORequest airplaneDTORequest) throws GalacticsAirlinesException {
        Airplane createdAirplane = AirplaneMapper.INSTANCE.airplaneDTORequestToAirplaneEntity(airplaneDTORequest);

        if (!AirplaneValidationUtils.isValidAirplane(createdAirplane)) {
            throw new GalacticsAirlinesException("Il manque un élément dans le JSON");
        }

        Optional<Airplane> existingAirplane = airplaneRepository.findByBrandAndModelAndManufacturingYear(createdAirplane.getBrand(), createdAirplane.getModel(), createdAirplane.getManufacturingYear());

        if (existingAirplane.isPresent()) {
            return AirplaneMapper.INSTANCE.airplaneEntityToAirplaneDTOResponse(existingAirplane.get());
        }

        return AirplaneMapper.INSTANCE.airplaneEntityToAirplaneDTOResponse(airplaneRepository.save(createdAirplane));
    }

    @Override
    public AirplaneDTOResponse updateAirplane(Long id, AirplaneDTORequest airplaneDTORequest) {
        if (id == null || airplaneRepository.findById(id).isEmpty()) {
            throw new GalacticsAirlinesException("Aucun vol en bdd");
        }

        Airplane updatedAirplane = AirplaneMapper.INSTANCE.airplaneDTORequestToAirplaneEntity(airplaneDTORequest);

        if (!AirplaneValidationUtils.isValidAirplane(updatedAirplane)) {
            throw new GalacticsAirlinesException("Il manque un élément dans le JSON");
        }

        updatedAirplane.setAirplaneId(id);
        airplaneRepository.save(updatedAirplane);
        return AirplaneMapper.INSTANCE.airplaneEntityToAirplaneDTOResponse(updatedAirplane);

    }

    @Override
    public void deleteAirplane(Long id) {
        if (id == null) {
            throw new GalacticsAirlinesException("Il manque un élément dans le JSON");
        }

        Airplane airplane = airplaneRepository.findById(id).orElse(null);
        if (airplane != null) {
            airplaneRepository.delete(airplane);
        } else {
            throw new GalacticsAirlinesException("Aucun vol en base");
        }
    }

    @Override
    public AirplaneDTOResponse getAirplane(Long id) throws GalacticsAirlinesException {
        if (id == null) {
            throw new GalacticsAirlinesException("Il manque un élément dans le json");
        }

        Airplane targetAirplane = airplaneRepository.findById(id).orElse(null);

        if (targetAirplane == null) {
            throw new GalacticsAirlinesException("Aucun flight en bdd");
        }
        return AirplaneMapper.INSTANCE.airplaneEntityToAirplaneDTOResponse(targetAirplane);
    }

    public Airplane findOrSaveAirplane(Airplane airplane) {
        return airplaneRepository.findByBrandAndModelAndManufacturingYear(
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturingYear()
        ).orElseGet(() -> airplaneRepository.save(airplane));
    }
}

