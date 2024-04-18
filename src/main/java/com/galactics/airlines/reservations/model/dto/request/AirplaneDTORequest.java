package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AirplaneDTORequest {
    @Schema(example = "Boeing")
    private String brand;
    @Schema(example = "747")
    private String model;
    @Schema(example = "2015")
    private int manufacturingYear;
}


