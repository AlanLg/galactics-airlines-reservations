package com.galactics.airlines.reservations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    private String employeeNumber;
}