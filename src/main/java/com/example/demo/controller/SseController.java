package com.example.demo.controller;

import com.example.demo.service.http.WebSseClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SseController {

    private final WebSseClientService webSseClientService;

    public SseController(WebSseClientService webSseClientService) {
        this.webSseClientService = webSseClientService;
    }

    @GetMapping("/get-sse-messages")
    public Flux<String> getSseMessages() {
        return webSseClientService.getSseMessages();
    }
}
