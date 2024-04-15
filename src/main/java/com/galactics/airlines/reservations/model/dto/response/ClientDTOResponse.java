package com.galactics.airlines.reservations.model.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter @Setter
public class ClientDTOResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private String passportNumber;
}
