package com.globalbooking.loginEmail.service;

import com.globalbooking.loginEmail.dto.RegisterRequest;
import com.globalbooking.loginEmail.repository.User;
import com.globalbooking.loginEmail.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> registerService(RegisterRequest request) {

        if (userRepository.existsByEmailId(request.getEmailId())) {
            return ResponseEntity
                    .status(403) // Conflict
                    .body("Email already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailId(request.getEmailId());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity
                .status(201) // Created
                .body("Registration successful");
    }
}