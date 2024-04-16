package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends Person {
    private String passportNumber;
}


