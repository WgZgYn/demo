package com.example.demo.event.events;

import com.example.demo.event.Event;
import lombok.Getter;

@Getter
public class ScheduledEvent extends Event {
    private final String msg;
    private final String cron;

    public ScheduledEvent(Object source, String msg, String cron) {
        super(source);
        this.msg = msg;
        this.cron = cron;
    }

    @Override
    public String getType() {
        return "ScheduleEvent";
    }
}
