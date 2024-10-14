package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/")
    String hello() {
        return "Hello World";
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
