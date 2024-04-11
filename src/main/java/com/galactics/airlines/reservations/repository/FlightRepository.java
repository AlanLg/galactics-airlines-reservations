package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByDepartureCityAndArrivalCityAndDepartureAirportAndArrivalAirportAndAirplane(
            String departureCity,
            String arrivalCity,
            Airport departureAirport,
            Airport arrivalAirport,
            Airplane airplane
    );
}