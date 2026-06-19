package com.example.jobpotral2.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequest(
        @NotBlank(message = "Company name is required") String name,

        String description,
        String website,

        @NotBlank(message = "Location is required") String location,

        String industry) {
}
