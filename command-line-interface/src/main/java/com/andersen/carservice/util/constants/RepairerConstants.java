package com.andersen.carservice.util.constants;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RepairerConstants {

    public static final String REPAIRER_NOT_FOUND_BY_ID = "Repairer with id = %s wasn't found";

    public static String notFoundById(UUID id) {
        return String.format(REPAIRER_NOT_FOUND_BY_ID, id);
    }
}
