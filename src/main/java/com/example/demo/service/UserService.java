package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repo.UserRepository;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userdto) {
        if (userdto == null) {
            logger.error("UserDTO is null");
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        try {
            User user = new User();
            user.setEmail(userdto.getEmail());
            user.setUsername(userdto.getUsername());
            user.setPassword(passwordEncoder.encode(userdto.getPassword()));
            user.setEnabled(true);

            userRepository.save(user);
            logger.info("User registered successfully: {}", user.getUsername());
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            throw new RuntimeException("Error registering user", e);
        }
    }
}