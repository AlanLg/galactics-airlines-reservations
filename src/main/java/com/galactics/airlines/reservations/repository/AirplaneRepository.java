package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Optional<Airplane> findByBrandAndModelAndManufacturingYear(String brand, String model, Integer manufacturingYear);

}