package com.galactics.airlines.reservations.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galactics.airlines.reservations.model.dto.request.AirplaneDTORequest;

public class AirplaneDTOResponse extends AirplaneDTORequest {
    @JsonIgnore
    private Long airplaneId;
}
