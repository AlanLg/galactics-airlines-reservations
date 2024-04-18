package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class FilterFlightDTORequest {
    private Optional<LocalDateTime> startDate;
    private Optional<LocalDateTime> endDate;
    private Optional<String> departureCity;
    private Optional<String> arrivalCity;
    private Optional<String> departureAirport;
    private Optional<String> arrivalAirport;
    private Optional<String> airplaneModel;
}
