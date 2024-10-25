package com.example.demo.service;


import com.example.demo.dto.Task;
import com.example.demo.dto.TaskFetchResult;
import com.example.demo.service.device.DeviceControlService;
import com.example.demo.service.http.TaskFetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTask {

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
