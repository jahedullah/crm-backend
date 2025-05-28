package com.relatiq.crm_backend.exception;

public class DriverNotOnlineException extends RuntimeException {
    public DriverNotOnlineException(String message) {
        super(message);
    }
}
