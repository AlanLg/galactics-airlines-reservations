package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long flightId;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
    private int numberOfSeats;
}