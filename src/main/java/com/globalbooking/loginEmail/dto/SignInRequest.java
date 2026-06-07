package com.globalbooking.loginEmail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SignInRequest {

    private String emailId;

    @Pattern(
            regexp = "^$|^[0-9]{10}$",
            message = "Invalid phone number"
    )
    private String mobileNumber;

    @NotBlank(message = "Password is required")
    private String password;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
