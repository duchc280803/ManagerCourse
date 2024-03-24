package com.example.managercourse.enums;

public enum ClassifyEnum {

    MON_NEN_TANG(1),

    MON_CHUYEN_SAU(2),

    MON_BO_TRO(3);

    private final int value;

    ClassifyEnum(int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }
}
