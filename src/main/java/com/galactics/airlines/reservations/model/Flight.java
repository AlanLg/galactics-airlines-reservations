package com.galactics.airlines.reservations.model;

import jakarta.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureCity;
    private String arrivalCity;
    private String departureDateTime;
    private String arrivalDateTime;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
    private int numberOfSeats;

    // Constructors, getters, and setters
}