package com.example.demo.event;

import com.example.demo.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventListener implements ApplicationListener<Event> {
    ApplicationEventPublisher applicationEventPublisher;
    SubscribeService subscribeService;


    EventListener(ApplicationEventPublisher applicationEventPublisher, SubscribeService subscribeService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.subscribeService = subscribeService;
    }

    @Override
    public void onApplicationEvent(Event event) {
        subscribeService.broadcast(event);
    }
}
