package com.andersen.carservice.entity.enums;

public enum OrderStatus {
    ACTIVE,
    ABANDONED,
    COMPLETED;

    public static boolean isValueOf(String value) {
        try {
            valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
