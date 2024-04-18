package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTORequest {
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private AirportDTORequest departureAirport;
    private AirportDTORequest arrivalAirport;
    private AirplaneDTORequest airplane;
    private int numberOfSeats;
}
