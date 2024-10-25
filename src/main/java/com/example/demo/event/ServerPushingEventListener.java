package com.example.demo.event;

import com.example.demo.service.device.DeviceControlService;
import com.example.demo.service.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerPushingEventListener implements ApplicationListener<ServerPushingEvent> {

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
