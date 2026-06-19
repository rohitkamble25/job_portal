package com.example.jobpotral2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jobpotral2.dto.UpdateProfileRequest;
import com.example.jobpotral2.dto.UserResponse;

@Service
public interface UserService {
    UserResponse updateProfile(UpdateProfileRequest request, String email);

    UserResponse updateResumeUrl(String resumeUrl, String email);

    List<UserResponse> getAllUsers();

    void toggleUserStatus(Long userId);

    UserResponse getUserById(Long userId);

}
