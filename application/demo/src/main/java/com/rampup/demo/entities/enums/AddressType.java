package com.rampup.demo.entities.enums;

public enum AddressType {

    HOMEADRESS(1),
    BUSINESSADDRESS(2),
    SHIPPINGADDRES(3);

    private int code;

    private AddressType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AddressType valueOf(int code) {
        for (AddressType value : AddressType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid AddressType code");
    }

}