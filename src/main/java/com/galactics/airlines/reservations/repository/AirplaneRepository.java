package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}