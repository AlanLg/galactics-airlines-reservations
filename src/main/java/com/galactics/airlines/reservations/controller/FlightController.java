package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.model.Flight;
import com.galactics.airlines.reservations.repository.FlightRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FlightController {

    private final FlightRepository repository;

    public FlightController(FlightRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Flight findById(@PathVariable long id) {
        return null;
    }
}
