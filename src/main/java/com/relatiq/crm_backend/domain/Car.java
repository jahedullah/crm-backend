package com.relatiq.crm_backend.domain;

import com.relatiq.crm_backend.enumtype.EngineType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    private Integer seatCount;
    private Boolean convertible;
    private Double rating;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    private String manufacturer;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime dateCreated;

    @PrePersist
    public void prePersist() {
        this.dateCreated = ZonedDateTime.now();
    }
}
