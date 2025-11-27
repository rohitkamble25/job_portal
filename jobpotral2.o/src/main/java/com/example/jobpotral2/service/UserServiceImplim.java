package com.example.jobpotral2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jobpotral2.model.User;
import com.example.jobpotral2.repo.UserRepo;

@Service
public class UserServiceImplim implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(User user) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }
        return user;
    }

}
