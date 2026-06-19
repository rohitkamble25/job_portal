package com.example.jobpotral2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import com.example.jobpotral2.enums.JobType;

public record JobRequest(
        @NotBlank(message = "Job title is required") String title,

        @NotBlank(message = "Job description is required") String description,

        @NotBlank(message = "Location is required") String location,

        @NotNull(message = "Job type is required") JobType jobType,

        Double salaryMin,
        Double salaryMax,
        String experience,
        String skills,
        Integer vacancies,
        LocalDate lastDate,

        @NotNull(message = "Company ID is required") Long companyId) {
}