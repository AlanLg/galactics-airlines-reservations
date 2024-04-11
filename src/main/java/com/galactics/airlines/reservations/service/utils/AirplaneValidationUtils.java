package com.galactics.airlines.reservations.service.utils;

import com.galactics.airlines.reservations.model.entity.Airplane;
import lombok.experimental.UtilityClass;

import static com.galactics.airlines.reservations.service.utils.ValidationUtils.isNotEmpty;

@UtilityClass
public class AirplaneValidationUtils {
    public static boolean isValidAirplane(Airplane airplane) {
        return airplane != null &&
                isNotEmpty(airplane.getBrand()) &&
                airplane.getManufacturingYear() != null;
    }
}
