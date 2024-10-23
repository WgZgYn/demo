package com.example.demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeviceEvent extends ApplicationEvent {
    private final String from;
    private final String msg;

    public DeviceEvent(Object source, String from, String msg) {
        super(source);
        this.from = from;
        this.msg = msg;
    }
}
