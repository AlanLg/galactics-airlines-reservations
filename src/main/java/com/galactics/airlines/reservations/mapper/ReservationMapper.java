package com.galactics.airlines.reservations.mapper;

import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.ReservationDTOResponse;
import com.galactics.airlines.reservations.model.entity.Client;
import com.galactics.airlines.reservations.model.entity.Flight;
import com.galactics.airlines.reservations.model.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "passportNumber", target = "passportNumber")
    ClientDTORequest reservationDTORequestToClientDTORequest(ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient);

    @Mapping(source = "client.firstname", target = "client.firstname")
    @Mapping(source = "client.lastname", target = "client.lastname")
    @Mapping(source = "client.address", target = "client.address")
    @Mapping(source = "client.email", target = "client.email")
    @Mapping(source = "client.phoneNumber", target = "client.phoneNumber")
    @Mapping(source = "client.birthday", target = "client.birthday")
    @Mapping(source = "client.passportNumber", target = "client.passportNumber")
    @Mapping(source = "flight.departureCity", target = "flight.departureCity")
    @Mapping(source = "flight.arrivalCity", target = "flight.arrivalCity")
    @Mapping(source = "flight.departureDateTime", target = "flight.departureDateTime")
    @Mapping(source = "flight.arrivalDateTime", target = "flight.arrivalDateTime")
    @Mapping(source = "flight.departureAirport", target = "flight.departureAirport")
    @Mapping(source = "flight.arrivalAirport", target = "flight.arrivalAirport")
    @Mapping(source = "flight.airplane", target = "flight.airplane")
    ReservationDTOResponse reservationEntityToReservationDTOResponse(Reservation reservation);

    @Mapping(source = "client.id", target = "client.id")
    @Mapping(source = "client.firstname", target = "client.firstname")
    @Mapping(source = "client.lastname", target = "client.lastname")
    @Mapping(source = "client.address", target = "client.address")
    @Mapping(source = "client.email", target = "client.email")
    @Mapping(source = "client.phoneNumber", target = "client.phoneNumber")
    @Mapping(source = "client.birthday", target = "client.birthday")
    @Mapping(source = "client.passportNumber", target = "client.passportNumber")
    @Mapping(source = "flight.departureCity", target = "flight.departureCity")
    @Mapping(source = "flight.arrivalCity", target = "flight.arrivalCity")
    @Mapping(source = "flight.departureDateTime", target = "flight.departureDateTime")
    @Mapping(source = "flight.arrivalDateTime", target = "flight.arrivalDateTime")
    @Mapping(source = "flight.departureAirport", target = "flight.departureAirport")
    @Mapping(source = "flight.arrivalAirport", target = "flight.arrivalAirport")
    @Mapping(source = "flight.airplane", target = "flight.airplane")
    Reservation clientEntityAndFlightEntityToReservationEntity(Client client, Flight flight);
}
