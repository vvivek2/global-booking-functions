package com.globalbooking.loginEmail.controller;

import com.globalbooking.loginEmail.dto.RegisterRequest;
import com.globalbooking.loginEmail.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return registerService.registerService(request);
        //return ResponseEntity.ok(message);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "User Registration Service is up and running!";
    }
}