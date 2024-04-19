package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AirportDTORequest {
    @NotBlank(message = "Airport name is mandatory")
    @Schema(example = "CDG")
    private String airportName;
    @NotBlank(message = "Country is mandatory")
    @Schema(example = "Paris")
    private String country;
    @NotBlank(message = "City is mandatory")
    @Schema(example = "France")
    private String city;
}
