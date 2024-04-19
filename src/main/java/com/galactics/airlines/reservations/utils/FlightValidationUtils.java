package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Flight;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.galactics.airlines.reservations.utils.AirplaneValidationUtils.isValidAirplane;
import static com.galactics.airlines.reservations.utils.AirportValidationUtils.isValidAirport;
import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

@UtilityClass
@Slf4j
public class FlightValidationUtils {
    public static boolean verifyElementInEntityToSave(Flight flight) {
        boolean isValid = isNotEmpty(flight.getDepartureCity()) ||
                isNotEmpty(flight.getArrivalCity()) ||
                isNotEmpty(String.valueOf(flight.getDepartureDateTime())) ||
                isNotEmpty(String.valueOf(flight.getArrivalDateTime())) ||
                isValidAirport(flight.getDepartureAirport()) ||
                isValidAirport(flight.getArrivalAirport()) ||
                isValidAirplane(flight.getAirplane());
        if (!isValid){
            log.info("Flight is not valid");
        }
        return isValid;
    }
}
