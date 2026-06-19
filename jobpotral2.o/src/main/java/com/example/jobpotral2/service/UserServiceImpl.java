package com.example.jobpotral2.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jobpotral2.dto.UpdateProfileRequest;
import com.example.jobpotral2.dto.UserResponse;
import com.example.jobpotral2.entity.User;
import com.example.jobpotral2.repo.UserRepo;
import com.example.jobpotral2.security.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    @Override
    public UserResponse updateProfile(UpdateProfileRequest request, String email) {
        User user = getUser(email);
        if (request.name() != null)
            user.setFullName(request.name());

        if (request.phoneNumber() != null)
            user.setPhoneNumber(request.phoneNumber());

        if (request.profileSummary() != null) {
            user.setProfileSummary(request.profileSummary());

        }

        return UserResponse.from(userRepo.save(user));

    }

    @Override
    public UserResponse updateResumeUrl(String resumeUrl, String email) {
        User user = getUser(email);
        user.setResumeUrl(resumeUrl);
        return UserResponse.from(userRepo.save(user));
    }

    @Override
    public void toggleUserStatus(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setActive(!user.isActive());
        userRepo.save(user);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return UserResponse.from(
                userRepo.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId)));
    }

    private User getUser(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .map(UserResponse::from).toList();
    }

}
