package com.relatiq.crm_backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class ApiResponse<T> {
    private final ZonedDateTime timestamp;
    private final int status;
    private final String message;
    private final T data;
}
