package com.example.demo.object;

import com.example.demo.object.abstractObject.EventListener;
import com.example.demo.object.abstractObject.IEvent;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventDispatcher {
    private EventDispatcher() {}
    @Getter
    private static final EventDispatcher instance = new EventDispatcher();

    private final Map<Class<?>, Set<EventListener<?>>> listeners = new HashMap<>();

    public <T extends IEvent<?>> void subscribe(Class<T> eventType, EventListener<T> listener) {
        System.out.println("Subscribing " + eventType + " to " + listener);

        listeners.computeIfAbsent(eventType, k -> new HashSet<>()).add(listener);
    }

    public <T extends IEvent<?>> void publish(T event) {
        System.out.println("Publishing " + event);

        Set<EventListener<?>> registeredListeners = listeners.get(event.getClass());
        if (registeredListeners != null) {
            for (EventListener<?> listener : registeredListeners) {
                // 使用反射调用 onEvent 方法
                @SuppressWarnings("unchecked")
                EventListener<T> eventListener = (EventListener<T>) listener;
                eventListener.onEvent(event);
            }
        }
    }
}
