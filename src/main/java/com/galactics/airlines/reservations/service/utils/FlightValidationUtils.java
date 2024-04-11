package com.galactics.airlines.reservations.service.utils;

import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.model.entity.Flight;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FlightValidationUtils {
    public static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isValidAirport(Airport airport) {
        return airport != null &&
                isNotEmpty(airport.getAirportName()) &&
                isNotEmpty(airport.getCity()) &&
                isNotEmpty(airport.getCountry());
    }

    public static boolean isValidAirplane(Airplane airplane) {
        return airplane != null &&
                isNotEmpty(airplane.getBrand()) &&
                airplane.getManufacturingYear() != null;
    }

    public static boolean verifyElementInEntityToSave(Flight flight) {
        return !isNotEmpty(flight.getDepartureCity()) ||
                !isNotEmpty(flight.getArrivalCity()) ||
                !isNotEmpty(flight.getDepartureDateTime()) ||
                !isNotEmpty(flight.getArrivalDateTime()) ||
                !isValidAirport(flight.getDepartureAirport()) ||
                !isValidAirport(flight.getArrivalAirport()) ||
                !isValidAirplane(flight.getAirplane());
    }
}
