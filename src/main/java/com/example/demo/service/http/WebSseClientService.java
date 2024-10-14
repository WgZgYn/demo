package com.example.demo.service.http;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class WebSseClientService {

    private final WebClient webClient;
    ActixAuthService actixAuthService;

    public WebSseClientService(WebClient webClient, ActixAuthService actixAuthService) {
        this.webClient = webClient;
        this.actixAuthService = actixAuthService;
    }

    public Flux<String> getSseMessages() {
        return webClient.get()
                .uri("/sse/test") // 替换为远端服务器的 SSE 端点
                .header("Content-Type", "text/event-stream")
                .header("Cache-Control", "no-cache")
                .retrieve()
                .bodyToFlux(String.class);
    }
}
