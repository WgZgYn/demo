package com.example.demo.service.device;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// FIXME maybe this class will be placed in repository
@Service
public class DeviceService {
    private final Map<String, List<String>> deviceServices = new HashMap<>();

    public List<String> getDeviceServices(String deviceId) {
        return deviceServices.get(deviceId);
    }
}
