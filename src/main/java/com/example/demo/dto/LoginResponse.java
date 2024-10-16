package com.example.demo.dto;


public record LoginResponse(int code, String message, long timestamp, Claims data) {
    public record Claims(String role, String token) {
    }
}


