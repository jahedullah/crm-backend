package com.relatiq.crm_backend.service;

import com.relatiq.crm_backend.domain.Car;
import com.relatiq.crm_backend.domain.Driver;
import com.relatiq.crm_backend.dto.CarResponseDto;
import com.relatiq.crm_backend.dto.DriverRequestDto;
import com.relatiq.crm_backend.dto.DriverResponseDto;
import com.relatiq.crm_backend.exception.CarAlreadyInUseException;
import com.relatiq.crm_backend.exception.CarNotFoundException;
import com.relatiq.crm_backend.exception.DriverNotFoundException;
import com.relatiq.crm_backend.exception.DriverNotOnlineException;
import com.relatiq.crm_backend.repository.CarRepository;
import com.relatiq.crm_backend.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;

    public List<DriverResponseDto> findAll() {
        return driverRepository.findAll().stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public DriverResponseDto findById(Long id) {
        return driverRepository.findById(id).map(this::toResponseDto).orElse(null);
    }

    public DriverResponseDto create(DriverRequestDto dto) {
        Driver driver = new Driver();
        BeanUtils.copyProperties(dto, driver);
        if (dto.getSelectedCarId() != null) {
            // Check if car is already assigned
            boolean inUse = driverRepository.findAll().stream()
                    .anyMatch(d -> d.getSelectedCar() != null && d.getSelectedCar().getId().equals(dto.getSelectedCarId()));
            if (inUse) {
                throw new CarAlreadyInUseException("Car is already assigned to another driver");
            }
            // Only assign car if driver is online
            if (!Boolean.TRUE.equals(dto.getOnlineStatus())) {
                throw new DriverNotOnlineException("Car can only be assigned to an online driver");
            }
            carRepository.findById(dto.getSelectedCarId()).ifPresent(driver::setSelectedCar);
        }
        driver = driverRepository.save(driver);
        return toResponseDto(driver);
    }

    public DriverResponseDto update(Long id, DriverRequestDto dto) {
        Optional<Driver> optional = driverRepository.findById(id);
        if (optional.isEmpty()) return null;
        Driver driver = optional.get();
        BeanUtils.copyProperties(dto, driver, "id", "dateCreated");
        if (dto.getSelectedCarId() != null) {
            // Check if car is already assigned
            boolean inUse = driverRepository.findAll().stream()
                    .anyMatch(d -> d.getSelectedCar() != null && d.getSelectedCar().getId().equals(dto.getSelectedCarId()) && !d.getId().equals(id));
            if (inUse) {
                throw new CarAlreadyInUseException("Car is already assigned to another driver");
            }
            // Only assign car if driver is online
            if (!Boolean.TRUE.equals(dto.getOnlineStatus())) {
                throw new DriverNotOnlineException("Car can only be assigned to an online driver");
            }
            carRepository.findById(dto.getSelectedCarId()).ifPresent(driver::setSelectedCar);
        } else {
            driver.setSelectedCar(null);
        }
        driver = driverRepository.save(driver);
        return toResponseDto(driver);
    }

    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    public DriverResponseDto assignCar(Long driverId, Long carId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException());
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException());
        boolean inUse = driverRepository.findAll().stream()
                .anyMatch(d -> d.getSelectedCar() != null && d.getSelectedCar().getId().equals(carId) && !d.getId().equals(driverId));
        if (inUse) {
            throw new CarAlreadyInUseException("Car is already assigned to another driver");
        }
        // Only assign car if driver is online
        if (!Boolean.TRUE.equals(driver.getOnlineStatus())) {
            throw new DriverNotOnlineException("Car can only be assigned to an online driver");
        }
        driver.setSelectedCar(car);
        driverRepository.save(driver);
        return toResponseDto(driver);
    }

    public DriverResponseDto unassignCar(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException());
        driver.setSelectedCar(null);
        driverRepository.save(driver);
        return toResponseDto(driver);
    }

    private DriverResponseDto toResponseDto(Driver driver) {
        DriverResponseDto dto = new DriverResponseDto();
        BeanUtils.copyProperties(driver, dto);
        if (driver.getSelectedCar() != null) {
            CarResponseDto carDto = new CarResponseDto();
            BeanUtils.copyProperties(driver.getSelectedCar(), carDto);
            dto.setSelectedCar(carDto);
        }
        return dto;
    }
}
