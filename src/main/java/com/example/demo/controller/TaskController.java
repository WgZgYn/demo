package com.example.demo.controller;

import com.example.demo.dto.TaskFetchResult;
import com.example.demo.service.http.TaskFetchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController("/test")
public class TaskController {
    private final TaskFetchService taskFetchService;

    public TaskController(TaskFetchService taskFetchService) {
        this.taskFetchService = taskFetchService;
    }

    @ResponseBody
    @GetMapping("/fetch")
    public TaskFetchResult fetchTask() throws JsonProcessingException {
        log.info("Controller Fetching task");
        return taskFetchService.fetchTask("wzy", "123456");
    }

    @PostMapping(value = "/echo", consumes = "text/plain")
    String echo(@RequestBody String body) {
        return "echo: " + body;
    }

    @GetMapping(value = "/ping")
    String ping() {
        return "pong";
    }
}

