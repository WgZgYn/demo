package com.example.demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }

    abstract public String getType();
}
