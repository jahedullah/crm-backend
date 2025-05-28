package com.relatiq.crm_backend.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relatiq.crm_backend.domain.Car;
import com.relatiq.crm_backend.domain.Driver;
import com.relatiq.crm_backend.enumtype.EngineType;
import com.relatiq.crm_backend.repository.CarRepository;
import com.relatiq.crm_backend.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        if (carRepository.count() == 0) {
            InputStream carStream = new ClassPathResource("cars_seed.json").getInputStream();
            List<Map<String, Object>> cars = objectMapper.readValue(carStream, new TypeReference<>() {});
            for (Map<String, Object> c : cars) {
                Car car = new Car();
                car.setLicensePlate((String) c.get("licensePlate"));
                car.setSeatCount((Integer) c.get("seatCount"));
                car.setConvertible((Boolean) c.get("convertible"));
                car.setEngineType(EngineType.valueOf((String) c.get("engineType")));
                car.setManufacturer((String) c.get("manufacturer"));
                carRepository.save(car);
            }
        }
        if (driverRepository.count() == 0) {
            InputStream driverStream = new ClassPathResource("drivers_seed.json").getInputStream();
            List<Map<String, Object>> drivers = objectMapper.readValue(driverStream, new TypeReference<>() {});
            for (Map<String, Object> d : drivers) {
                Driver driver = new Driver();
                driver.setName((String) d.get("name"));
                driver.setOnlineStatus((Boolean) d.get("onlineStatus"));
                driverRepository.save(driver);
            }
        }
    }
}
