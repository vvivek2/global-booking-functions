package com.globalbooking.loginOtp.dto;

public class OtpVerifyRequest {

    private String countryCode;
    private String mobileNumber;
    private String otp;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFullNumber() {
        return countryCode + mobileNumber;
    }
}