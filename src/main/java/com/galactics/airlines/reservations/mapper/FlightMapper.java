package com.galactics.airlines.reservations.mapper;

import com.galactics.airlines.reservations.model.dto.request.FlightDTORequest;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.model.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(source = "departureCity", target = "departureCity")
    @Mapping(source = "arrivalCity", target = "arrivalCity")
    @Mapping(source = "departureDateTime", target = "departureDateTime")
    @Mapping(source = "arrivalDateTime", target = "arrivalDateTime")
    @Mapping(source = "departureAirport", target = "departureAirport")
    @Mapping(source = "arrivalAirport", target = "arrivalAirport")
    @Mapping(source = "airplane", target = "airplane")
    Flight flightDTORequestToFlightEntity(FlightDTORequest flightDTORequest);

    @Mapping(source = "departureCity", target = "departureCity")
    @Mapping(source = "arrivalCity", target = "arrivalCity")
    @Mapping(source = "departureDateTime", target = "departureDateTime")
    @Mapping(source = "arrivalDateTime", target = "arrivalDateTime")
    @Mapping(source = "departureAirport", target = "departureAirport")
    @Mapping(source = "arrivalAirport", target = "arrivalAirport")
    @Mapping(source = "airplane", target = "airplane")
    FlightDTOResponse flightEntityToFlightDTOResponse(Flight flight);

    List<FlightDTOResponse> flightEntitiesToFlightDTOResponses(List<Flight> flights);

}
