package com.example.demo.dto;

import lombok.Getter;


@Getter
public class MqttEventMessage {
    String from;
    String event;

    private MqttEventMessage(String from, String event) {
        this.from = from;
        this.event = event;
    }

    @Getter
    public static class Error extends Throwable {
        String message;
        public Error(String message) {
            this.message = message;
        }
    }

    public static MqttEventMessage from(byte[] payload) throws Error {
        String message = new String(payload);
        String[] parts = message.split(",");
        if (parts.length != 2) {
            throw new Error("payload format error");
        }

        String from = parts[0];
        String event = parts[1];

        return new MqttEventMessage(from, event);
    }
}
