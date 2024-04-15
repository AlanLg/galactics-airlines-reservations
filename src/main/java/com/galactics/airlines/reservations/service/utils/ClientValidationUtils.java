package com.galactics.airlines.reservations.service.utils;

import com.galactics.airlines.reservations.model.entity.Client;

import java.util.Objects;

import static com.galactics.airlines.reservations.service.utils.ValidationUtils.isNotEmpty;

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
