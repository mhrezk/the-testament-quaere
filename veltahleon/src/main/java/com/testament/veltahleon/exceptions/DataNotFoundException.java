package com.testament.veltahleon.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {}

    public DataNotFoundException(String message) {
        super(message);
    }
}
