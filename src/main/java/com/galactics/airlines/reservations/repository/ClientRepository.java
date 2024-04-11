package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}