package com.example.managercourse.enums;

public enum SubjectEnum {

    CHUAN_BI_HOC(1),

    DANG_HOC(2),

    DA_XONG(3),

    CHUA_HOC(4),

    DANG_THI(5);

    private final int value;

    SubjectEnum(int value) { this.value = value; }

    private int getValue() { return value; }
}
