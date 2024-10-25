package com.example.demo.controller;

import com.example.demo.dto.SubscribeRequest;
import com.example.demo.event.Event;
import com.example.demo.event.EventFactory;
import com.example.demo.service.SubscribeService;
import com.example.demo.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeController {
    SubscribeService subscribeService;

    SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }


    @ResponseBody
    @PostMapping("/subscribe")
    Result subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        Event event;
        try {
            event = EventFactory.createEvent(subscribeRequest.getEventType(), this, subscribeRequest.getSource(), subscribeRequest.getEvent());
        } catch (Exception e) {
            return Result.Err("Not Support Event Type");
        }
        subscribeService.subscribe(event, subscribeRequest.getSubscriber(), subscribeRequest.getAction());
        return Result.Ok();
    }
}
