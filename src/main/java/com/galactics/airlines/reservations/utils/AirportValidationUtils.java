package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Airport;
import lombok.experimental.UtilityClass;

import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

@UtilityClass
public class AirportValidationUtils {
    public static boolean isValidAirport(Airport airport) {
        return airport != null &&
                isNotEmpty(airport.getAirportName()) &&
                isNotEmpty(airport.getCity()) &&
                isNotEmpty(airport.getCountry());
    }
}
