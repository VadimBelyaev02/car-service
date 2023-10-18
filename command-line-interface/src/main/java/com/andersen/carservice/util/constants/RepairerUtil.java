package com.andersen.carservice.util.constants;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RepairerUtil {

    public static final String REPAIRER_NOT_FOUND_BY_ID = "Repairer with id = %s wasn't found";

    public static final String ALREADY_EXISTS_BY_EMAIL = "Repairer with email = %s already exists";
    public static String notFoundById(UUID id) {
        return String.format(REPAIRER_NOT_FOUND_BY_ID, id);
    }

    public static String alreadyExistsByEmail(String email) {
        return String.format(ALREADY_EXISTS_BY_EMAIL, email);
    }
}
