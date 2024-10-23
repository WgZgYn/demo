package com.example.demo.object.abstractObject;

public interface IAction<In extends IEvent<?>, Out extends IEvent<?>> {
    Out execute(In event);
}
