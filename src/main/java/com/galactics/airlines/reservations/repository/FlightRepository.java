package com.galactics.airlines.reservations.repository;

import com.galactics.airlines.reservations.model.Flight;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FlightRepository {

    public final List<Flight> flights;

    public FlightRepository() {
        flights = new ArrayList<>();
        flights.add(new Flight(1));
    }
}
