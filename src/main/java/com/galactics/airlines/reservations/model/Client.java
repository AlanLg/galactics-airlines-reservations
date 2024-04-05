package com.galactics.airlines.reservations.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Client extends Person {
    private String passportNumber;
}
