package com.example.demo.service;


import com.example.demo.dto.Task;
import com.example.demo.dto.TaskFetchResult;
import com.example.demo.service.http.TaskFetchService;
import com.example.demo.service.mqtt.MqttClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private final DeviceControlService deviceControlService;
    private final TaskFetchService taskFetchService;

    ScheduledTask(DeviceControlService deviceControlService, TaskFetchService taskFetchService) {
        this.deviceControlService = deviceControlService;
        this.taskFetchService = taskFetchService;
    }


    //    @Scheduled(fixedRate = 20000, initialDelay = 10000)
    public void performTask() {
        try {
            // 执行的操作
            log.info("[SCHEDULED]: Performing task...");
            TaskFetchResult result = taskFetchService.fetchTask("wzy", "123456");
            if (result != null) {
                for (Task task : result.tasks()) {
                    boolean code = deviceControlService.deviceOps(task.device_id(), task.action());
                    log.info("performTask {}", code ? "ok" : "err");
                }
            }
            log.info("[SCHEDULED]: Finished performing task..., result: {}", result);
        } catch (Exception e) {
            log.error("[SCHEDULED]: Error while performing task", e);
        }
    }
}
