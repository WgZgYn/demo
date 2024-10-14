package com.example.demo.controller;

import com.example.demo.dto.TaskFetchResult;
import com.example.demo.service.http.TaskFetchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

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

