package com.galactics.airlines.reservations.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class ReservationDTORequest {
    @Schema(example = "john.doe@gmail.com")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    @Size(min = 3, message = "Email must be at least 3 chars")
    private String email;
    @Schema(example = "New York")
    private String departureCity;
    @Schema(example = "Paris")
    private String arrivalCity;
    @Schema(example = "2024-04-18T11:00:00.000Z")
    private LocalDateTime departureDateTime;
    @Schema(example = "2024-04-18T18:00:00.000Z")
    private LocalDateTime arrivalDateTime;
}
