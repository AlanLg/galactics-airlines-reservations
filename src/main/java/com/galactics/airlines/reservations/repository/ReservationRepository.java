package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}