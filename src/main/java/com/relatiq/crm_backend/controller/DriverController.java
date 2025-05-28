package com.relatiq.crm_backend.controller;

import com.relatiq.crm_backend.dto.ApiResponse;
import com.relatiq.crm_backend.dto.DriverRequestDto;
import com.relatiq.crm_backend.dto.DriverResponseDto;
import com.relatiq.crm_backend.enumtype.EngineType;
import com.relatiq.crm_backend.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Retrieves a list of all drivers.
     * @return a response with all drivers
     */
/* <<<<<<<<<<  057d2825-7098-46c1-9b16-7a25059f8d3c  >>>>>>>>>>> */
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
     * @param id the ID of the driver
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

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Delete a driver.
     *
     * @param id the ID of the driver
     * @return a response with a status of 200 and a message of "Deleted"
     */
/* <<<<<<<<<<  d8193885-21ff-436d-b3d6-874f02a48099  >>>>>>>>>>> */
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

    /**
     * Filter drivers based on the given parameters.
     *
     * @param engineType the {@link EngineType} of the cars to filter by
     * @param convertible whether to only include drivers with a convertible car
     * @param minRating the minimum {@link Car#getRating()} to filter by
     * @param maxRating the maximum {@link Car#getRating()} to filter by
     * @return a response with the filtered list of drivers
     */
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<DriverResponseDto>>> filterDrivers(
            @RequestParam(value = "engineType", required = false) EngineType engineType,
            @RequestParam(value = "convertible", required = false) Boolean convertible,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating
    ) {
        List<DriverResponseDto> filtered = driverService.filterDrivers(engineType, convertible, minRating, maxRating);
        return ResponseEntity.ok(
            ApiResponse.<List<DriverResponseDto>>builder()
                .status(200)
                .message("Filtered")
                .data(filtered)
                .timestamp(java.time.ZonedDateTime.now())
                .build()
        );
    }
}
