package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Flight;
import lombok.experimental.UtilityClass;

import static com.galactics.airlines.reservations.utils.AirplaneValidationUtils.isValidAirplane;
import static com.galactics.airlines.reservations.utils.AirportValidationUtils.isValidAirport;
import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

@UtilityClass
public class FlightValidationUtils {
    public static boolean verifyElementInEntityToSave(Flight flight) {
        return isNotEmpty(flight.getDepartureCity()) ||
                isNotEmpty(flight.getArrivalCity()) ||
                isNotEmpty(flight.getDepartureDateTime()) ||
                isNotEmpty(flight.getArrivalDateTime()) ||
                isValidAirport(flight.getDepartureAirport()) ||
                isValidAirport(flight.getArrivalAirport()) ||
                isValidAirplane(flight.getAirplane());
    }
}
