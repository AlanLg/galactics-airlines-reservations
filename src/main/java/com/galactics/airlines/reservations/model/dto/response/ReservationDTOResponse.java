package com.galactics.airlines.reservations.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTOResponse {
    private FlightDTOResponse flight;
    private ClientDTOResponse client;
}
