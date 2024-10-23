package com.example.demo.object;

import com.example.demo.object.abstractObject.*;
import lombok.Getter;

@Getter
public class Light extends Entity
        implements Publisher<Light.LightEvent>, Subscriber<Sun.SunEvent> {

    private LightState lightState;

    Light() {
        // register Event
        subscribe(Sun.SunEvent.class);

        // Default Device status
        lightState = LightState.Closed;
    }

    public enum LightState {
        Open,
        Closed,
    }

    public static class LightEvent implements IEvent<LightState> {
        LightState lightState;

        public LightEvent(LightState lightState) {
            this.lightState = lightState;
        }

        @Override
        public LightState getMetaData() {
            return lightState;
        }
    }

    public class LightAction implements IAction<Sun.SunEvent, LightEvent> {
        public void open() {
            lightState = LightState.Open;
        }

        public void close() {
            lightState = LightState.Closed;
        }


        @Override
        public LightEvent execute(Sun.SunEvent event) {
            Sun.SunState state = event.getMetaData();
            switch (state) {
                case SunRise -> close();
                case SunSet -> open();
            }
            return new LightEvent(lightState);
        }
    }

    @Override
    public void onEvent(Sun.SunEvent event) {
        System.out.println("Light onEvent");
        publish(new LightAction().execute(event));
    }

    @Override
    public String getName() {
        return "Device";
    }

    @Override
    public long uuid() {
        return 1;
    }
}
