package com.galactics.airlines.reservations.model.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}