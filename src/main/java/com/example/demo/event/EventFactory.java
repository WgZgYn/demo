package com.example.demo.event;

import com.example.demo.event.events.A;

import java.lang.reflect.InvocationTargetException;

public class EventFactory {
    private static final String packagePrefix = Event.class.getPackageName() + ".events";

    public static Event createEvent(String className, Object object, String source, String event) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println(packagePrefix);
        System.out.println(packagePrefix + "." + className);
        Class<?> eventClass = Class.forName(packagePrefix + "." + className);
        return (Event) eventClass.getDeclaredConstructor(Object.class, String.class, String.class).newInstance(object, source, event);
    }


    // Test OK
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Event e = createEvent("DeviceEvent", 1, "0", "open");
        System.out.println(e);

//        A a = (A)Class.forName(packagePrefix + "." + "A").getDeclaredConstructor(String.class).newInstance("asdasdas");
//        System.out.println(a.name);
    }
}
