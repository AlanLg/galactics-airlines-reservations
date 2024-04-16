package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Client;

import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

public class ClientValidationUtils {
    public static boolean isValidClient(Client client) {
        return client != null &&
                isNotEmpty(client.getFirstname()) &&
                isNotEmpty(client.getLastname()) &&
                isNotEmpty(client.getAddress()) &&
                isNotEmpty(client.getEmail()) &&
                isNotEmpty(client.getPhoneNumber()) &&
                isNotEmpty(client.getPassportNumber());
//                Objects.nonNull(client.getBirthday());
    }
}
