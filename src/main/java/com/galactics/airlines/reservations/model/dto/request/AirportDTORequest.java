package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AirportDTORequest {
    @Schema(example = "CDG")
    private String airportName;
    @Schema(example = "Paris")
    private String country;
    @Schema(example = "France")
    private String city;
}
