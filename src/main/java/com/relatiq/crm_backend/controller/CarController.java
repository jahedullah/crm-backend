package com.relatiq.crm_backend.controller;

import com.relatiq.crm_backend.dto.ApiResponse;
import com.relatiq.crm_backend.dto.CarRequestDto;
import com.relatiq.crm_backend.dto.CarResponseDto;
import com.relatiq.crm_backend.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CarResponseDto>>> getAll() {
        return ResponseEntity.ok(
            ApiResponse.<List<CarResponseDto>>builder()
                .status(200)
                .message("Success")
                .data(carService.findAll())
                .timestamp(java.time.ZonedDateTime.now())
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CarResponseDto>> getById(@PathVariable Long id) {
        CarResponseDto dto = carService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
            ApiResponse.<CarResponseDto>builder()
                .status(200)
                .message("Success")
                .data(dto)
                .timestamp(java.time.ZonedDateTime.now())
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CarResponseDto>> create(@Valid @RequestBody CarRequestDto dto) {
        CarResponseDto created = carService.create(dto);
        return ResponseEntity.ok(
            ApiResponse.<CarResponseDto>builder()
                .status(201)
                .message("Created")
                .data(created)
                .timestamp(java.time.ZonedDateTime.now())
                .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CarResponseDto>> update(@PathVariable Long id, @Valid @RequestBody CarRequestDto dto) {
        CarResponseDto updated = carService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
            ApiResponse.<CarResponseDto>builder()
                .status(200)
                .message("Updated")
                .data(updated)
                .timestamp(java.time.ZonedDateTime.now())
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.ok(
            ApiResponse.<Void>builder()
                .status(200)
                .message("Deleted")
                .data(null)
                .timestamp(java.time.ZonedDateTime.now())
                .build()
        );
    }
}
