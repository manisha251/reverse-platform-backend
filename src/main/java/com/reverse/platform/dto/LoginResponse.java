package com.reverse.platform.dto;

public class LoginResponse {
    private String role;
    private Object data;

    public LoginResponse() {}

    public LoginResponse(String role, Object data) {
        this.role = role;
        this.data = data;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
