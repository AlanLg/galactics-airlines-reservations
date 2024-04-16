package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

import java.util.Optional;

@Data
public class FilterFlightDTORequest {
    private Optional<String> startDate;
    private Optional<String> endDate;
    private Optional<String> departureCity;
    private Optional<String> arrivalCity;
    private Optional<String> departureAirport;
    private Optional<String> arrivalAirport;
    private Optional<String> airplaneModel;
}
