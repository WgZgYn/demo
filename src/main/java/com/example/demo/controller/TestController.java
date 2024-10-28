package com.example.demo.controller;

import com.example.demo.service.mqtt.MqttClientService;
import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    MqttClientService mqttClientService;

    public TestController(MqttClientService mqttClientService) {
        this.mqttClientService = mqttClientService;
    }

    @GetMapping("/test/mqtt")
    public Result testMqtt() {
        try {
            mqttClientService.publish("162756773954388/service", "switch");
        } catch (Exception e) {
            return Result.Err(e.getMessage());
        }
        return Result.Ok();
    }
}
