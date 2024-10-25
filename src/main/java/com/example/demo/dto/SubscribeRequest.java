package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeRequest {
    String subscriber;
    String eventType;
    String source;
    String event;
    String action;
}
