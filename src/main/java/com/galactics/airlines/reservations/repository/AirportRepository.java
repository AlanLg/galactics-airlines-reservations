package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByAirportNameAndCountryAndCity(String airportName, String country, String city);
}