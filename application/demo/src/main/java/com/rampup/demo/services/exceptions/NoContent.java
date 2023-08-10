package com.rampup.demo.services.exceptions;

public class NoContent extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoContent(String msg) {
        super(msg);
    }
}
