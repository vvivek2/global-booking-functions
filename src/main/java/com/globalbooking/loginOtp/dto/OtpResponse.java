package com.globalbooking.loginOtp.dto;

public class OtpResponse {
    private String message;
    private String messageSid;

    public OtpResponse(String message, String messageSid) {
        this.message = message;
        this.messageSid = messageSid;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageSid() {
        return messageSid;
    }
}