package com.relatiq.crm_backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DriverResponseDto {
    private Long id;
    private String name;
    private Boolean onlineStatus;
    private CarResponseDto selectedCar;
    private ZonedDateTime dateCreated;
}
