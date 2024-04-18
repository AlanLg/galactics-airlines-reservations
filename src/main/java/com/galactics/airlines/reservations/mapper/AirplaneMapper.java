package com.galactics.airlines.reservations.mapper;

import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirplaneDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airplane;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirplaneMapper {
    AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "manufacturingYear", target = "manufacturingYear")
    Airplane airplaneDTORequestToAirplaneEntity(AirplaneDTORequest airplaneDTORequest);

    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "model", target = "model")
    @Mapping(source = "manufacturingYear", target = "manufacturingYear")
    AirplaneDTOResponse airplaneEntityToAirplaneDTOResponse(Airplane airplane);
}
