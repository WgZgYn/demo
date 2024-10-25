package com.example.demo.service;

import com.example.demo.event.events.ScheduledEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduledEventService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ThreadPoolTaskScheduler taskScheduler;

    // TODO: replaced with postgres
    private final List<ScheduledFuture<?>> scheduledEvents = new ArrayList<>();


    public ScheduledEventService(ThreadPoolTaskScheduler taskScheduler, ApplicationEventPublisher applicationEventPublisher) {
        taskScheduler.initialize();
        this.taskScheduler = taskScheduler;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public int addScheduledEvent(ScheduledEvent event) {
        return scheduleEvent(event);
    }

    public List<ScheduledFuture<?>> getAllScheduledEvents() {
        return scheduledEvents;
    }

    public void deleteScheduledEvent(int id) {
        for (ScheduledFuture<?> scheduledEvent : scheduledEvents) {
            if (scheduledEvent.hashCode() == id) {
                scheduledEvent.cancel(true);
            }
        }
    }

    // avoid same hashcode
    private int scheduleEvent(ScheduledEvent event) {
        ScheduledFuture<?> handler = taskScheduler.schedule(() -> triggerEvent(event), new CronTrigger("0/20 * * * * *"));
        return handler.hashCode();
    }

    private void triggerEvent(ScheduledEvent event) {
        // 执行事件触发逻辑
        applicationEventPublisher.publishEvent(event);
    }
}

