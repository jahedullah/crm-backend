package com.relatiq.crm_backend.service;

import com.relatiq.crm_backend.domain.Car;
import com.relatiq.crm_backend.dto.CarRequestDto;
import com.relatiq.crm_backend.dto.CarResponseDto;
import com.relatiq.crm_backend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarResponseDto> findAll() {
        return carRepository.findAll().stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public CarResponseDto findById(Long id) {
        return carRepository.findById(id).map(this::toResponseDto).orElse(null);
    }

    public CarResponseDto create(CarRequestDto dto) {
        Car car = new Car();
        BeanUtils.copyProperties(dto, car);
        car.setRating(null); // Ensure rating is not set at creation
        car = carRepository.save(car);
        return toResponseDto(car);
    }

    public CarResponseDto update(Long id, CarRequestDto dto) {
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isEmpty()) return null;
        Car car = optional.get();
        Double prevRating = car.getRating();
        BeanUtils.copyProperties(dto, car, "id", "dateCreated", "rating"); // Don't overwrite rating
        car.setRating(prevRating); // Explicitly preserve rating
        car = carRepository.save(car);
        return toResponseDto(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    private CarResponseDto toResponseDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        BeanUtils.copyProperties(car, dto);
        return dto;
    }
}
