package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}