package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Employee extends Person {
    private String employeeNumber;
}