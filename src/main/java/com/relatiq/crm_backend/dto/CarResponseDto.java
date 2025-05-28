package com.relatiq.crm_backend.dto;

import com.relatiq.crm_backend.enums.EngineType;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CarResponseDto {
    private Long id;
    private String licensePlate;
    private Integer seatCount;
    private Boolean convertible;
    private Double rating;
    private EngineType engineType;
    private String manufacturer;
    private ZonedDateTime dateCreated;
}
