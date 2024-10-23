package com.example.demo.object.abstractObject;

import com.example.demo.object.EventDispatcher;

public interface Publisher<O extends IEvent<?>> {
    default void publish(O event) {
        EventDispatcher.getInstance().publish(event);
    }
}
