package com.example.jobpotral2.dto;

public record UpdateProfileRequest(
        String name,
        String phoneNumber,
        String profileSummary) {
}