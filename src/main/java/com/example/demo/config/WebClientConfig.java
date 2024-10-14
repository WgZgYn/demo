package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(@Value("${actix-web.http.api.url}") String apiUrl) {
        return WebClient.builder()
                .baseUrl(apiUrl) // 替换为远端服务器的 URL
                .build();
    }
}
