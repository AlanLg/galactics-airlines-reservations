package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Reservation;

import java.util.Objects;

import static com.galactics.airlines.reservations.utils.ClientValidationUtils.isValidClient;

public class ReservationValidationUtils {
    public static boolean isValidReservation(Reservation reservation) {
        return reservation != null &&
                isValidClient(reservation.getClient()) &&
                Objects.nonNull(reservation.getFlight()
                );
    }
}
