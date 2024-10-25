package com.example.demo.event.events;

import com.example.demo.event.Event;
import lombok.Getter;

@Getter
public class DeviceEvent extends Event {
    private final String from;
    private final String msg;

    public DeviceEvent(Object source, String from, String msg) {
        super(source);
        this.from = from;
        this.msg = msg;
    }

    @Override
    public String getType() {
        return "DeviceEvent";
    }
}