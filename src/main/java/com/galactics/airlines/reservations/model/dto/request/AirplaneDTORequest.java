package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

@Data
public class AirplaneDTORequest {
    private Long airplaneId;
    private String brand;
    private String model;
    private int manufacturingYear;
}


