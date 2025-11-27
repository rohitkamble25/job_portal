package com.example.jobpotral2.service;

import org.springframework.stereotype.Service;

import com.example.jobpotral2.model.User;

@Service
public interface UserService {
    User register(User user);

    User login(String email, String password);
}
