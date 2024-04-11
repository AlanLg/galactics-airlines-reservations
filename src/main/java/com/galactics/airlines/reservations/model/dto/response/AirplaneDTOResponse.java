package com.galactics.airlines.reservations.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galactics.airlines.reservations.model.dto.request.AirportDTORequest;

public class AirplaneDTOResponse extends AirportDTORequest {
    @JsonIgnore
    private Long id;
}
