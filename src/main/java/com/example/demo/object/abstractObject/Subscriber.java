package com.example.demo.object.abstractObject;

import com.example.demo.object.EventDispatcher;

public interface Subscriber<I extends IEvent<?>> extends EventListener<I> {
    default void subscribe(Class<I> eventType) {
        EventDispatcher.getInstance().subscribe(eventType, this);
    }
}
