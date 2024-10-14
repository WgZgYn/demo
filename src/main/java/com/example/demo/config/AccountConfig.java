package com.example.demo.config;

import com.example.demo.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {
    @Bean
    public LoginRequest loginRequest(@Value("${auth.username}") String username, @Value("${auth.password}") String password) {
        return new LoginRequest(username, password);
    }
}
