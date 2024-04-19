package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class FlightDTORequest {
    @NotBlank(message = "Departure city is mandatory")
    @Schema(example = "New York")
    private String departureCity;
    @NotBlank(message = "Arrival city is mandatory")
    @Schema(example = "Paris")
    private String arrivalCity;
    @Schema(example = "2024-04-18T11:00:00.000Z")
    private LocalDateTime departureDateTime;
    @Schema(example = "2024-04-18T18:00:00.000Z")
    private LocalDateTime arrivalDateTime;
    private AirportDTORequest departureAirport;
    private AirportDTORequest arrivalAirport;
    private AirplaneDTORequest airplane;
    @Schema(example = "500")
    private int numberOfSeats;
}
