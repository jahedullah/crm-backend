package com.relatiq.crm_backend.exception;

import com.relatiq.crm_backend.dto.ApiResponse;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CarAlreadyInUseException.class)
    public ResponseEntity<ApiResponse<Object>> handleCarAlreadyInUse(CarAlreadyInUseException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<Object>> handleJwtSignatureException(SignatureException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid or tampered JWT token.")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({IllegalArgumentException.class, org.springframework.http.converter.HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResponse<Object>> handleInvalidEnum(Exception ex) {
        String message = "Invalid value provided for an enum field. Please use a valid value.";
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleDriverNotFound(DriverNotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCarNotFound(CarNotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DriverNotOnlineException.class)
    public ResponseEntity<ApiResponse<Object>> handleDriverNotOnline(DriverNotOnlineException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
