package com.relatiq.crm_backend.exception;

public class CarAlreadyInUseException extends RuntimeException {
    public CarAlreadyInUseException(String message) {
        super(message);
    }
}
