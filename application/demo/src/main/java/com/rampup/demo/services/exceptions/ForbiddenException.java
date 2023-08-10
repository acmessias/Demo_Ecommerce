package com.rampup.demo.services.exceptions;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException(Object id) {
        super("Request not Authorized.");
    }
}
