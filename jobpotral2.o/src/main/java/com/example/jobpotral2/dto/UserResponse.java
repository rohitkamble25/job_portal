package com.example.jobpotral2.dto;

import java.time.LocalDateTime;

import com.example.jobpotral2.entity.User;
import com.example.jobpotral2.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        Role role,
        String profileSummary,
        String resumeUrl,
        boolean isActive,
        LocalDateTime createdAt) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole(),
                user.getProfileSummary(),
                user.getResumeUrl(),
                user.isActive(),
                user.getCreatedAt());
    }
}
