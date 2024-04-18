package com.galactics.airlines.reservations.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTORequestWithNoExistingClient extends ClientDTORequest {
    private String departureCity;
    private String arrivalCity;
    private String departureDateTime;
    private String arrivalDateTime;
}
