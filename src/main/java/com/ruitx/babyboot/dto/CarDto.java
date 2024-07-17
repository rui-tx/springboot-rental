package com.ruitx.babyboot.dto;

import jakarta.validation.constraints.NotNull;

public record CarDto(

        // not null for now
        long id,
        
        @NotNull(message = "Car model is required")
        String model,

        @NotNull(message = "Car year is required")
        int year,

        @NotNull(message = "Car kms is required")
        int kms
) {
}
