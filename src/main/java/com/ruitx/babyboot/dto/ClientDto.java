package com.ruitx.babyboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDto(
        @NotNull
        @NotBlank(message = "Client first name is required")
        String firstName,

        @NotNull
        @NotBlank(message = "Client last name is required")
        String lastName
) {
}
