package com.relatiq.crm_backend.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super("Car not found");
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
