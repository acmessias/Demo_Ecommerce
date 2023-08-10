package com.rampup.demo.services.exceptions;

public class InvalidDocumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDocumentException(String DocumentNumber) {
        super("Document " + DocumentNumber + " is not valid.");
    }
}
