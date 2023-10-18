package com.andersen.carservice.model.entity.enums;

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
