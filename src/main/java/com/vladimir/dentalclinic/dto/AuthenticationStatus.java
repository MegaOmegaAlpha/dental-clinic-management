package com.vladimir.dentalclinic.dto;

public class AuthenticationStatus {

    private String status;

    public AuthenticationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
