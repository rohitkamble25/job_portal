package com.example.jobpotral2.dto;

import jakarta.validation.constraints.NotNull;

public record ApplicationRequest(
        @NotNull(message = "Job ID is required") Long jobId,

        String coverLetter,
        String resumeUrl) {
}
