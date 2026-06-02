package com.globalbooking.loginOtp.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class OtpStoreService {

    private final RedisTemplate<String, String> redisTemplate;

    private static final long OTP_EXPIRY = 5; // minutes

    public OtpStoreService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveOtp(String mobile, String otp) {
        String key = "OTP:" + normalize(mobile);
        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(OTP_EXPIRY));
    }

    public String getOtp(String mobile) {
        String key = "OTP:" + normalize(mobile);
        return redisTemplate.opsForValue().get(key);
    }

    public void clearOtp(String mobile) {
        redisTemplate.delete("OTP:" + normalize(mobile));
    }

    private String normalize(String mobile) {
        return mobile.replaceAll("\\s+", "");
    }

    private String buildKey(String mobile) {
        return "OTP:" + mobile;
    }
}