package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long airportId;
    private String airportName;
    private String country;
    private String city;

    public Long getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
}