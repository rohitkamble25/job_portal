package com.example.jobpotral2.dto;

import java.time.LocalDateTime;

import com.example.jobpotral2.entity.JobApplication;
import com.example.jobpotral2.enums.ApplicationStatus;

public record ApplicationResponse(
        Long id,
        Long jobId,
        String jobTitle,
        String companyName,
        Long applicantId,
        String applicantName,
        String applicantEmail,
        ApplicationStatus status,
        String coverLetter,
        String resumeUrl,
        String employerNotes,
        LocalDateTime appliedAt,
        LocalDateTime updatedAt) {
    public static ApplicationResponse from(JobApplication app) {
        return new ApplicationResponse(
                app.getId(),
                app.getJob().getId(),
                app.getJob().getTitle(),
                app.getJob().getCompany().getName(),
                app.getApplicant().getId(),
                app.getApplicant().getFullName(),
                app.getApplicant().getEmail(),
                app.getStatus(),
                app.getCoverLetter(),
                app.getResumeUrl(),
                app.getEmployerNotes(),
                app.getAppliedAt(),
                app.getUpdatedAt());
    }
}
