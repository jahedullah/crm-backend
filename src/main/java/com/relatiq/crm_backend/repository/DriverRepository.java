package com.relatiq.crm_backend.repository;

import com.relatiq.crm_backend.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
