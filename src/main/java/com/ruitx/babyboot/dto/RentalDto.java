package com.ruitx.babyboot.dto;

import jakarta.validation.constraints.NotNull;

public record RentalDto(
        @NotNull(message = "Client is required")
        ClientDto client,
        @NotNull(message = "Car is required")
        CarDto car,
        @NotNull(message = "Start date is required")
        String startDate,
        @NotNull(message = "End date is required")
        String endDate
) {
}
