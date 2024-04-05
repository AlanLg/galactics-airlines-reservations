package com.galactics.airlines.reservations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    private String passportNumber;

    // Constructors, getters, and setters
}
