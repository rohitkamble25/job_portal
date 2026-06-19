package com.example.jobpotral2.dto;

import com.example.jobpotral2.enums.Role;

public record AuthResponse(
        String token,
        String type,
        Long userId,
        String name,
        String email,
        Role role) {
    public AuthResponse(String token, Long userId, String name, String email, Role role) {
        this(token, "Bearer", userId, name, email, role);
    }
}
