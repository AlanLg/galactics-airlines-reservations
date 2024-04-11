package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}