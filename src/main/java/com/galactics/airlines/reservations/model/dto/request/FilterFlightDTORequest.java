package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class FilterFlightDTORequest {
    private Optional<LocalDateTime> startDate;
    private Optional<LocalDateTime> endDate;
    @Schema(example = "New York")
    private Optional<String> departureCity;
    @Schema(example = "Paris")
    private Optional<String> arrivalCity;
    @Schema(example = "CDG")
    private Optional<String> departureAirport;
    @Schema(example = "JFK Airport")
    private Optional<String> arrivalAirport;
    @Schema(example = "Boeing")
    private Optional<String> airplaneBrand;
}
