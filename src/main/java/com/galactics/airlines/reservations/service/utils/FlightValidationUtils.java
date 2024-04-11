package com.galactics.airlines.reservations.service.utils;

import com.galactics.airlines.reservations.model.entity.Airplane;
import com.galactics.airlines.reservations.model.entity.Airport;
import com.galactics.airlines.reservations.model.entity.Flight;
import lombok.experimental.UtilityClass;

import static com.galactics.airlines.reservations.service.utils.AirplaneValidationUtils.isValidAirplane;
import static com.galactics.airlines.reservations.service.utils.AirportValidationUtils.isValidAirport;
import static com.galactics.airlines.reservations.service.utils.ValidationUtils.isNotEmpty;

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
