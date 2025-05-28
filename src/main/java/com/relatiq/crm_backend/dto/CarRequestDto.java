package com.relatiq.crm_backend.dto;

import com.relatiq.crm_backend.enums.EngineType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarRequestDto {
    @NotBlank
    private String licensePlate;

    @NotNull
    @Min(1)
    private Integer seatCount;

    @NotNull
    private Boolean convertible;

    @NotNull
    private EngineType engineType;

    @NotBlank
    private String manufacturer;
}
