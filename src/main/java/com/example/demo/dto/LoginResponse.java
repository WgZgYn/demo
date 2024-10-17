package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(int code, String message, long timestamp, @JsonProperty("data") Claims claims) {
    public record Claims(String role, String token) {
    }
}


