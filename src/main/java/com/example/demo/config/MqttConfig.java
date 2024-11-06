package com.example.demo.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class MqttConfig {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Configuration
    @ConfigurationProperties(prefix = "mqtt.topic")
    public static class Topic {
        private String[] subscribeTopics;
        private int[] subscribeQos;
        private String deviceEvents;
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Configuration
    @ConfigurationProperties(prefix = "mqtt.client")
    public static class Client {
        public String broker;
        public String clientId;
    }
}
