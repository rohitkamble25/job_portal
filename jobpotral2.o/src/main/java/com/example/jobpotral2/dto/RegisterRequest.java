package com.example.jobpotral2.dto;

import com.example.jobpotral2.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
                @NotBlank(message = "Name is required") String name,

                @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

                @NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password,

                String phoneNumber,

                @NotNull(message = "Role is required") Role role) {
}
