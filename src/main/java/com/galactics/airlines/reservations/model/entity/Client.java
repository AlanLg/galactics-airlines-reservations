package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Client extends Person {
    private String passportNumber;
}


