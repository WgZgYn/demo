package com.example.demo.object;

/*
This just a try to implement an Easy Event-driven programming.
 */

public class Scene {
    public static final Sun sun = Sun.getInstance();

    public static void main(String[] args) {
        Light light = new Light();
        sun.publish(new Sun.SunEvent(Sun.SunState.SunSet));
        System.out.println(light.getLightState());
        sun.publish(new Sun.SunEvent(Sun.SunState.SunRise));
        System.out.println(light.getLightState());
    }
}
