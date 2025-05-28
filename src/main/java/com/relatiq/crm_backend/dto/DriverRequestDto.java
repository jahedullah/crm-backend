package com.relatiq.crm_backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DriverRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private Boolean onlineStatus;

    private Long selectedCarId; // Optional: for assignment during creation/update
}
