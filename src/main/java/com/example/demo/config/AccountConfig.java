package com.example.demo.config;

import com.example.demo.dto.LoginRequest;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AccountConfig {
    String username;
    String password;


    @Bean
    public LoginRequest loginRequest() {
        return new LoginRequest(username, password);
    }
}
