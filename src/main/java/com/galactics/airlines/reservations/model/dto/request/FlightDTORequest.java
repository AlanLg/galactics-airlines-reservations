package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

@Data
public class FlightDTORequest {
    private String departureCity;
    private String arrivalCity;
    private String departureDateTime;
    private String arrivalDateTime;
    private AirportDTORequest departureAirport;
    private AirportDTORequest arrivalAirport;
    private AirplaneDTORequest airplane;
    private int numberOfSeats;
}
