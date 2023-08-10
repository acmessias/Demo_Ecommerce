package com.rampup.demo.entities.enums;

public enum POState {

    ACTIVE(1),
    DEFINITION(2),
    TECHNICAL(3);

    private int code;

    private POState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static POState valueOf(int code) {
        for (POState value : POState.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid POState code");
    }

}