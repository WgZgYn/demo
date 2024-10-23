package com.example.demo.service;

import com.example.demo.dto.ScheduledEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledEventService {
    private ThreadPoolTaskScheduler taskScheduler;
    private final List<ScheduledEvent> scheduledEvents = new ArrayList<>();


    public ScheduledEventService(ThreadPoolTaskScheduler taskScheduler) {
        taskScheduler.initialize();
    }

    public ScheduledEvent addScheduledEvent(ScheduledEvent event) {
        scheduledEvents.add(event);
        scheduleEvent(event);
        return event;
    }

    public List<ScheduledEvent> getAllScheduledEvents() {
        return scheduledEvents;
    }

    public void deleteScheduledEvent(Long id) {
        scheduledEvents.removeIf(event -> event.getId().equals(id));
        // 需要实现取消调度任务的逻辑
    }

    private void scheduleEvent(ScheduledEvent event) {
        taskScheduler.schedule(() -> triggerEvent(event.getEventName()), Instant.parse(event.getStartTime()));
    }

    private void triggerEvent(String eventName) {
        // 执行事件触发逻辑
    }
}

