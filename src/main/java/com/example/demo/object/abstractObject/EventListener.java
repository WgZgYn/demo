package com.example.demo.object.abstractObject;

public interface EventListener<T extends IEvent<?>> {
    void onEvent(T event);
}
