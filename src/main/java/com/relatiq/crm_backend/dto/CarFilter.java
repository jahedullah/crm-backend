package com.relatiq.crm_backend.dto;

import com.relatiq.crm_backend.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarFilter {
    private EngineType engineType;
    private Boolean convertible;
    private Double minRating;
    private Double maxRating;
}
