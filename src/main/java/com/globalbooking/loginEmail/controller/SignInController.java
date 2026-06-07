package com.globalbooking.loginEmail.controller;

import com.globalbooking.loginEmail.dto.RegisterRequest;
import com.globalbooking.loginEmail.dto.SignInRequest;
import com.globalbooking.loginEmail.service.RegisterService;
import com.globalbooking.loginEmail.service.SignInService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SignInController {

    private final SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest request) {
        return signInService.signInService(request);
    }
}