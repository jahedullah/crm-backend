package com.relatiq.crm_backend.controller;

import com.relatiq.crm_backend.dto.ApiResponse;
import com.relatiq.crm_backend.dto.DriverRequestDto;
import com.relatiq.crm_backend.dto.DriverResponseDto;
import com.relatiq.crm_backend.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DriverResponseDto>>> getAll() {
        return ResponseEntity.ok(
                ApiResponse.<List<DriverResponseDto>>builder()
                        .status(200)
                        .message("Success")
                        .data(driverService.findAll())
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverResponseDto>> getById(@PathVariable Long id) {
        DriverResponseDto dto = driverService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                ApiResponse.<DriverResponseDto>builder()
                        .status(200)
                        .message("Success")
                        .data(dto)
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DriverResponseDto>> create(@Valid @RequestBody DriverRequestDto dto) {
        DriverResponseDto created = driverService.create(dto);
        return ResponseEntity.ok(
                ApiResponse.<DriverResponseDto>builder()
                        .status(201)
                        .message("Created")
                        .data(created)
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }

    /**
     * Update a driver.
     *
     * @param id  the ID of the driver
     * @param dto the request body containing the new driver data
     * @return a response with the updated driver data
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverResponseDto>> update(@PathVariable Long id, @Valid @RequestBody DriverRequestDto dto) {
        DriverResponseDto updated = driverService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                ApiResponse.<DriverResponseDto>builder()
                        .status(200)
                        .message("Updated")
                        .data(updated)
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }

    /**
     * Delete a driver.
     *
     * @param id the ID of the driver
     * @return a response with a status of 200 and a message of "Deleted"
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        driverService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .status(200)
                        .message("Deleted")
                        .data(null)
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }

    @PostMapping("/{driverId}/assign-car/{carId}")
    public ResponseEntity<ApiResponse<DriverResponseDto>> assignCar(@PathVariable Long driverId, @PathVariable Long carId) {
        DriverResponseDto dto = driverService.assignCar(driverId, carId);
        return ResponseEntity.ok(
                ApiResponse.<DriverResponseDto>builder()
                        .status(200)
                        .message("Car assigned")
                        .data(dto)
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }

    @PostMapping("/{driverId}/unassign-car")
    public ResponseEntity<ApiResponse<DriverResponseDto>> unassignCar(@PathVariable Long driverId) {
        DriverResponseDto dto = driverService.unassignCar(driverId);
        return ResponseEntity.ok(
                ApiResponse.<DriverResponseDto>builder()
                        .status(200)
                        .message("Car unassigned")
                        .data(dto)
                        .timestamp(java.time.ZonedDateTime.now())
                        .build()
        );
    }
}
