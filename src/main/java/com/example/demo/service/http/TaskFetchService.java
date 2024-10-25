package com.example.demo.service.http;

import com.example.demo.dto.TaskFetchRequestBody;
import com.example.demo.dto.TaskFetchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@Service
public class TaskFetchService {
    private static final String address = "http://47.108.27.238:80/api/task";
    static ObjectMapper objectMapper = new ObjectMapper();

    ActixAuthService actixAuthService;
    WebClient webClient;

    public TaskFetchService(ActixAuthService actixAuthService, WebClient webClient) {
        this.actixAuthService = actixAuthService;
        this.webClient = webClient;
    }

    public TaskFetchResult fetchTask(String username, String password) throws JsonProcessingException {
        String requestBody = objectMapper.writeValueAsString(new TaskFetchRequestBody(username, password));
        log.info("[TEST]: Sending request json {} to {}", requestBody, address);

//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(address))
//                    .header("Content-Type", "application/json")
//                    .method("GET", HttpRequest.BodyPublishers.ofString(requestBody))
//                    .build();
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            String responseBody = response.body();
//            log.info("[TEST]: Received response body {}", responseBody);
//            return objectMapper.readValue(responseBody, TaskFetchResult.class);
//        } catch (Exception e) {
//            log.error("[ERROR]: Exception occurred while fetching task", e);
//        }

        return webClient
                .get()
                .uri("/task")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + actixAuthService.getToken())
                .retrieve()
                .bodyToMono(TaskFetchResult.class)
                .block();
    }
}
