package com.example.managercourse.enums;

public enum DayEnums {

    THU2_THU4_THU6(1),

    THU3_THU5_THU7(2);

    private final int value;

    DayEnums(int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }
}
