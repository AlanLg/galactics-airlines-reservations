package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByFlight_FlightIdAndClient_Id(Long flightId, Long clientId);
}