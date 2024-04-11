package com.galactics.airlines.reservations.service.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtils {
    public static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
