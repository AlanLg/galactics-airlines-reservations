package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ClientDTORequest {
    @Schema(example = "John")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @Schema(example = "Doe")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @Schema(example = "22 Jump Street")
    @NotBlank(message = "address is mandatory")
    private String address;
    @Schema(example = "john.doe@gmail.com")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    @Size(min = 3, message = "Email must be at least 3 chars")
    private String email;
    @Schema(example = "0123456789")
    @NotBlank(message = "phonenumber is mandatory")
    private String phoneNumber;
    @Schema(example = "11/11/1990")
    @NotNull(message = "birthday is mandatory")
    private Date birthday;
    @Schema(example = "1234567890")
    @NotBlank(message = "passportNumber is mandatory")
    private String passportNumber;
}
