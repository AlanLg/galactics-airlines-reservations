package com.galactics.airlines.reservations.model.dto.request;

import lombok.Data;

@Data
public class AirportDTORequest {
    private String airportName;
    private String country;
    private String city;
}
