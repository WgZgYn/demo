package com.example.demo.service;

import com.example.demo.event.Event;
import com.example.demo.service.device.DeviceControlService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SubscribeService {
    private final DeviceControlService deviceControlService;

    // TODO: replaced with postgresql
    private final Map<Event, Map<String, String>> subs = new HashMap<>();

    SubscribeService(DeviceControlService deviceControlService) {
        this.deviceControlService = deviceControlService;
    }

    public void subscribe(Event event, String subscriber, String action) {
        subs.getOrDefault(event, new HashMap<>()).put(subscriber, action);
    }

    public void unsubscribe(Event event, String subscriber) {
        if (subs.containsKey(event)) {
            subs.get(event).remove(subscriber);
        }
    }

    public Set<String> getSubscribers(Event event) {
        if (subs.containsKey(event)) {
            return subs.get(event).keySet();
        }
        return new HashSet<>();
    }

    public void broadcast(Event event) {
        if (subs.containsKey(event)) {
            subs.get(event).forEach(deviceControlService::send_event);
        }
    }
}
