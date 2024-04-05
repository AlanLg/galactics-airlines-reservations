package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FlightController {

    @GetMapping("/{id}")
    public Flight findById(@PathVariable long id) {
        return null;
    }
}
