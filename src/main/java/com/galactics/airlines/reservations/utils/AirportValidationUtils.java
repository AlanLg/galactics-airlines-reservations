package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Airport;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

@UtilityClass
@Slf4j
public class AirportValidationUtils {
    public static boolean isValidAirport(Airport airport) {
        boolean isValid = airport != null &&
                isNotEmpty(airport.getAirportName()) &&
                isNotEmpty(airport.getCity()) &&
                isNotEmpty(airport.getCountry());
        if (!isValid){
            log.info("Airport is not valid");
        }
        return isValid;
    }
}
