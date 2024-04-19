package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Airplane;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

@UtilityClass
@Slf4j
public class AirplaneValidationUtils {
    public static boolean isValidAirplane(Airplane airplane) {
        boolean isValid = airplane != null &&
                isNotEmpty(airplane.getBrand()) &&
                airplane.getManufacturingYear() != null;
        if (!isValid){
            log.info("Airplane is not valid");
        }
        return isValid;
    }
}
