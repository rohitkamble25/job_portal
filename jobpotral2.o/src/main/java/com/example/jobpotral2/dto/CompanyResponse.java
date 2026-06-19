package com.example.jobpotral2.dto;

import java.time.LocalDateTime;

import com.example.jobpotral2.entity.Company;

public record CompanyResponse(
        Long id,
        String name,
        String description,
        String website,
        String location,
        String industry,
        String logoUrl,
        boolean isApproved,
        String employerName,
        Long employerId,
        LocalDateTime createdAt) {
    public static CompanyResponse from(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getWebsite(),
                company.getLocation(),
                company.getIndustry(),
                company.getLogourl(),
                company.isApproved(),
                company.getEmployer().getFullName(),
                company.getEmployer().getId(),
                company.getCreatedAt());
    }
}
