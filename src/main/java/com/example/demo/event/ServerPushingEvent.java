package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class ServerPushingEvent extends ApplicationEvent {
    private final String msg;

    public ServerPushingEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }
}
