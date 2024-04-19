package com.galactics.airlines.reservations.utils;

import com.galactics.airlines.reservations.model.entity.Client;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.galactics.airlines.reservations.utils.ValidationUtils.isNotEmpty;

@UtilityClass
@Slf4j
public class ClientValidationUtils {
    public static boolean isValidClient(Client client) {
        boolean isValid = client != null &&
                isNotEmpty(client.getFirstname()) &&
                isNotEmpty(client.getLastname()) &&
                isNotEmpty(client.getAddress()) &&
                isNotEmpty(client.getEmail()) &&
                isNotEmpty(client.getPhoneNumber()) &&
                isNotEmpty(client.getPassportNumber());
        if (!isValid){
            log.info("Client is not valid");
        }
        return isValid;
    }
}
