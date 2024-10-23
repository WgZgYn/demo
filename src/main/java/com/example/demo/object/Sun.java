package com.example.demo.object;

import com.example.demo.object.abstractObject.Entity;
import com.example.demo.object.abstractObject.IEvent;
import com.example.demo.object.abstractObject.Publisher;
import lombok.Getter;

public class Sun extends Entity implements Publisher<Sun.SunEvent> {
    private Sun() {}
    @Getter
    private static final Sun instance = new Sun();


    public enum SunState {
        SunRise,
        SunSet,
    }

    public static class SunEvent implements IEvent<SunState> {
        SunState sunState;
        public SunEvent(SunState sunState) {
            this.sunState = sunState;
        }

        @Override
        public SunState getMetaData() {
            return sunState;
        }
    }

    @Override
    public String getName() {
        return "Sun";
    }

    @Override
    public long uuid() {
        return 0;
    }
}
