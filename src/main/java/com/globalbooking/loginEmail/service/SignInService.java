package com.globalbooking.loginEmail.service;

import com.globalbooking.loginEmail.dto.SignInRequest;
import com.globalbooking.loginEmail.repository.User;
import com.globalbooking.loginEmail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signInService(SignInRequest request) {

        User user = null;

        // Login using email
        if (request.getEmailId() != null && !request.getEmailId().isEmpty()) {
            user = userRepository.findByEmailId(request.getEmailId())
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(404).body("No account found with this email");
            }
        }

        // Login using mobile number
        if (request.getMobileNumber() != null && !request.getMobileNumber().isEmpty()) {
            user = userRepository.findByMobileNumber(request.getMobileNumber())
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(404).body("No account found with this mobile number");
            }
        }

        // Validate password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Incorrect password");
        }

        return ResponseEntity.status(200).body("Login successful");
    }
}

