package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Reservation;

import java.util.Objects;

public class ReservationValidationUtils {
    public static boolean isValidReservation(Reservation reservation) {
        return reservation != null &&
                Objects.nonNull(reservation.getClient()) &&
                Objects.nonNull(reservation.getFlight()
                );
    }
}
