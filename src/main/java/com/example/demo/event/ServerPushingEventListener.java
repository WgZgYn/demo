package com.example.demo.event;

import com.example.demo.service.DeviceControlService;
import com.example.demo.service.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ServerPushingEventListener implements ApplicationListener<ServerPushingEvent> {
    private static final Logger log = LoggerFactory.getLogger(ServerPushingEventListener.class);

    DeviceControlService deviceControlService;
    ScheduledTask scheduledTask;

    ServerPushingEventListener(DeviceControlService deviceControlService, ScheduledTask scheduledTask) {
        this.deviceControlService = deviceControlService;
        this.scheduledTask = scheduledTask;
    }

    @Override
    public void onApplicationEvent(ServerPushingEvent event) {
        log.info("Received server pushing event: {}", event);
        scheduledTask.performTask(); // Using local-network tcp
    }
}
