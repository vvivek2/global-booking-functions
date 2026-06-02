package com.globalbooking.loginOtp.controller;

import com.globalbooking.loginOtp.dto.OtpRequest;
import com.globalbooking.loginOtp.dto.OtpResponse;
import com.globalbooking.loginOtp.service.OtpService;
import com.globalbooking.loginOtp.service.OtpStoreService;
import com.globalbooking.loginOtp.service.SmsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;
    private final SmsService smsService;
    private final OtpStoreService otpStoreService;

    public OtpController(OtpService otpService, SmsService smsService, OtpStoreService otpStoreService) {
        this.otpService = otpService;
        this.smsService = smsService;
        this.otpStoreService = otpStoreService;
    }

    @PostMapping("/get")
    public OtpResponse getOtp(@RequestBody OtpRequest request) {

        String fullNumber = request.getFullNumber();
        fullNumber = fullNumber.replaceAll("\\s+", "").trim();
        String otp = otpService.generateOtp();

        smsService.sendOtp(fullNumber, otp);

        otpStoreService.saveOtp(fullNumber, otp);

        return new OtpResponse(
                "OTP sent successfully to " + fullNumber,
                null
        );
    }
}