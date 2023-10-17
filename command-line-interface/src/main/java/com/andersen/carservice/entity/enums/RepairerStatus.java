package com.andersen.carservice.entity.enums;

public enum RepairerStatus {
    ACTIVE,
    INACTIVE;

    public boolean isValueOf(String value) {
        try {
            valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
