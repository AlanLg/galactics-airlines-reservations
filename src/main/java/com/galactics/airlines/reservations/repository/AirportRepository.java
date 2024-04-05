package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}