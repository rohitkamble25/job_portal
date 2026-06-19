package com.example.jobpotral2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.jobpotral2.entity.Job;
import com.example.jobpotral2.enums.JobType;

public record JobResponse(
        Long id,
        String title,
        String description,
        String location,
        JobType jobType,
        Double salaryMin,
        Double salaryMax,
        String experience,
        String skills,
        Integer vacancies,
        LocalDate lastDate,
        boolean isActive,
        String companyName,
        Long companyId,
        String companyLogoUrl,
        String postedByName,
        LocalDateTime createdAt) {
    public static JobResponse from(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getJobType(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getExperience(),
                job.getSkills(),
                job.getVacancies(),
                job.getLastDate(),
                job.isActive(),
                job.getCompany().getName(),
                job.getCompany().getId(),
                job.getCompany().getLogourl(),
                job.getPostedBy().getFullName(),
                job.getCreatedAt());
    }
}
