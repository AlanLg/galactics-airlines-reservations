package com.galactics.airlines.reservations.model;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Employee extends Person {
    private String employeeNumber;
}