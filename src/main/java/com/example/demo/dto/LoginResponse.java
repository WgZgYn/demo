package com.example.demo.dto;


public class LoginResponse {
    int code;
    String message;
    long timestamp;
    Claims data;
    public LoginResponse() {}
    public LoginResponse(int code, String message, Claims data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public Claims getData() {
        return data;
    }
    public void setData(Claims data) {
        this.data = data;
    }

    public static class Claims {
        String role;
        String token;
        public Claims() {}
        public Claims(String role, String token) {
            this.role = role;
            this.token = token;
        }
        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }
    }
}


