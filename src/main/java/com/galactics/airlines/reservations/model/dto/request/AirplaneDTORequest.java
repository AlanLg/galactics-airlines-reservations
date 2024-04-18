package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

@Data
public class AirplaneDTORequest {
    private String brand;
    private String model;
    private int manufacturingYear;
}


