package com.example.demo.event;

import com.example.demo.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeviceEventListener implements ApplicationListener<DeviceEvent> {
    ApplicationEventPublisher applicationEventPublisher;
    SubscribeService subscribeService;


    DeviceEventListener(ApplicationEventPublisher applicationEventPublisher, SubscribeService subscribeService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.subscribeService = subscribeService;
    }

    @Override
    public void onApplicationEvent(DeviceEvent event) {

    }
}
