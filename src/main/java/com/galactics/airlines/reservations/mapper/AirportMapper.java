package com.galactics.airlines.reservations.mapper;

import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;
import com.galactics.airlines.reservations.model.dto.response.AirportDTOResponse;
import com.galactics.airlines.reservations.model.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirportMapper {
    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(source = "airportName", target = "airportName")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    Airport airportDTORequestToAirportEntity(AirportDTORequest airportDTORequest);

    @Mapping(source = "airportName", target = "airportName")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    AirportDTOResponse airportEntityToAirportDTOResponse(Airport airport);
}
