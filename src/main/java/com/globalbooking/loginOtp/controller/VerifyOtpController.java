package com.globalbooking.loginOtp.controller;

import com.globalbooking.loginOtp.dto.OtpVerifyRequest;
import com.globalbooking.loginOtp.service.OtpStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class VerifyOtpController {

    private final OtpStoreService otpStoreService;

    public VerifyOtpController(OtpStoreService otpStoreService) {
        this.otpStoreService = otpStoreService;
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerifyRequest request) {
        String fullNumber = request.getFullNumber().replaceAll("\\s+", "").trim();
        String storedOtp = otpStoreService.getOtp(fullNumber);

        if (storedOtp == null) {
            return ResponseEntity.status(HttpStatus.GONE)
                    .body("OTP expired or not found");
        }

        if (storedOtp.equals(request.getOtp())) {
            otpStoreService.clearOtp(fullNumber);
            return ResponseEntity.ok("OTP verified successfully");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid OTP");
    }
}