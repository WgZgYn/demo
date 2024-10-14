package com.example.demo.service.http;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ActixAuthService {
    private static final Logger log = LoggerFactory.getLogger(ActixAuthService.class);
    private final WebClient webClient;
    private final LoginRequest loginRequest;
    private String token = "";
    private String role = "";

    public ActixAuthService(WebClient webClient, LoginRequest loginRequest) {
        this.webClient = webClient;
        this.loginRequest = loginRequest;
    }

    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return webClient.post()
                .uri("/login")  // 发送到登录的 URI
                .header("Content-Type", "application/json")
                .bodyValue(loginRequest)  // 包含用户名和密码的请求体
                .retrieve()
                .bodyToMono(LoginResponse.class);  // 假设返回的 JWT token 是字符串
    }

    @PostConstruct
    public void init() {
        log.info("login and fetch token");
        LoginResponse response = login(loginRequest).block();
        if (response != null) {
            this.token = response.getData().getToken();
            this.role = response.getData().getRole();
        } else {
            log.error("login failed");
        }
        log.info("token: {},  role: {}", token, role);
    }

    public String getToken() {
        return token;
    }

//    public Mono<String> getAccountInfo(String authToken) {
//        return webClient.get()
//                .uri("/account")
//                .header("Authorization", "Bearer " + authToken)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
}
