package com.rampup.demo.entities.enums;

public enum CharacteristicType {

    USER(1),
    INTERNAL(2),
    TECHNICAL(3);

    private int code;

    private CharacteristicType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CharacteristicType valueOf(int code) {
        for (CharacteristicType value : CharacteristicType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid CharacteristicType code");
    }

}