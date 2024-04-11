package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long flightId;
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


    public Long getFlightId() {
        return flightId;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}