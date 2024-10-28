package com.example.demo.controller;

import com.example.demo.service.device.DeviceControlService;
import com.example.demo.service.device.DeviceDataService;
import com.example.demo.util.Response;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/device")
public class DeviceController {
    private final DeviceDataService deviceDataService;
    private final DeviceControlService deviceControlService;

    public DeviceController(
            DeviceDataService deviceDataService,
            DeviceControlService deviceControlService
    ) {
        this.deviceDataService = deviceDataService;
        this.deviceControlService = deviceControlService;
    }

    @GetMapping("/{id}/{ops}")
    public Result lightOps(@PathVariable String id, @PathVariable String ops) {
        if (deviceControlService.deviceOps(id, ops)) {
            return Result.Ok();
        }
        return Result.Err("err");
    }

    @GetMapping("/list")
    public Response<List<String>> list() {
        return Response.success(deviceDataService.listDevices());
    }

    @GetMapping("/connect/{ip}")
    public Result connect(@PathVariable String ip) {
        if (deviceControlService.connect(ip)) {
            return Result.Ok();
        }
        return Result.Err("err");
    }

    @GetMapping("/{id}/service")
    public Response<String> service(@PathVariable String id) {
        return Response.success(id);
    }
}

