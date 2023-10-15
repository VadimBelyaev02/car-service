package com.andersen.carservice.util.constants;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class OrderConstants {

    public static final String ORDER_NOT_FOUND_BY_ID = "Order with id = %s wasn't found";

    public static String notFoundById(UUID id) {
        return String.format(ORDER_NOT_FOUND_BY_ID, id);
    }
}
