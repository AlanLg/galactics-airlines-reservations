package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}