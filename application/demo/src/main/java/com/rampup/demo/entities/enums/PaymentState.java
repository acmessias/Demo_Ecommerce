package com.rampup.demo.entities.enums;

public enum PaymentState {

    PAID(1),
    PENDING(2),
    DENIED(3);

    private int code;

    private PaymentState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentState valueOf(int code) {
        for (PaymentState value : PaymentState.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentState code");
    }

}