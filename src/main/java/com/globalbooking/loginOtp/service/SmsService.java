package com.globalbooking.loginOtp.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    @Value("${twilio.phoneNumber}")
    private String fromNumber;

    public String sendOtp(String to, String otp) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(fromNumber),
                    "Your OTP is: " + otp
            ).create();

            log.info("Twilio message created, sid={}, to={}", message.getSid(), to);
            return message.getSid();
        } catch (ApiException e) {
            log.error("Twilio API error while sending OTP to {}: code={}, message={}", to, e.getCode(), e.getMessage(), e);
            throw e; // or return null/throw a custom exception depending on your flow
        } catch (Exception e) {
            log.error("Unexpected error while sending OTP to {}", to, e);
            throw e;
        }
    }
}