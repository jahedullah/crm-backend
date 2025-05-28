package com.relatiq.crm_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean onlineStatus;

    @OneToOne
    @JoinColumn(name = "selected_car_id")
    private Car selectedCar;

    private ZonedDateTime dateCreated;
}
