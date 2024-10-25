package com.example.demo.controller;

import com.example.demo.dto.TaskFetchResult;
import com.example.demo.service.http.TaskFetchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class TaskController {
    private final TaskFetchService taskFetchService;

    public TaskController(TaskFetchService taskFetchService) {
        this.taskFetchService = taskFetchService;
    }

    @ResponseBody
    @GetMapping("/test/fetch")
    public TaskFetchResult fetchTask() throws JsonProcessingException {
        log.info("Controller Fetching task");
        return taskFetchService.fetchTask("wzy", "123456");
    }
}

