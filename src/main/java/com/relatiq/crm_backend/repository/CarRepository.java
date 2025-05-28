package com.relatiq.crm_backend.repository;

import com.relatiq.crm_backend.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
