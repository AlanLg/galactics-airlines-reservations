package com.galactics.airlines.reservations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Employee {
    @Id
    private String employeeNumber;
}