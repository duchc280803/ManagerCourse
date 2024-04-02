package com.example.managercourse.enums;

public enum StatusContactEnum {

    CHO_XAC_NHAN(1),

    DANG_XU_LY(2),

    KHONG_NGHE_MAY(3),

    HOC_VIEN(4),

    KHAC(5);

    private final int value;

    StatusContactEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
