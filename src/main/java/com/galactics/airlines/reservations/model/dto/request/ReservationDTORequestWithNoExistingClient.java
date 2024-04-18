package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ReservationDTORequestWithNoExistingClient extends ReservationDTORequest {
    @Schema(example = "John")
    private String firstname;
    @Schema(example = "Doe")
    private String lastname;
    @Schema(example = "22 Jump Street")
    private String address;
    @Schema(example = "0123456789")
    private String phoneNumber;
    @Schema(example = "11/11/1990")
    private Date birthday;
    @Schema(example = "1234567890")
    private String passportNumber;
}
