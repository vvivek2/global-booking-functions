package com.globalbooking.loginOtp.dto;

public class OtpRequest {

    private String countryCode;
    private String mobileNumber;

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

    public String getFullNumber() {
        return countryCode + mobileNumber;
    }
}