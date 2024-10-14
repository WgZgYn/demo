package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceDataService {
    private final Map<String, String> devices = new HashMap<>();

    public List<String> listDevices() {
        return new ArrayList<>(devices.keySet());
    }

    public boolean hasDevice(String deviceId) {
        return devices.containsKey(deviceId);
    }

    public void addDevice(String deviceId, String data) {
        devices.put(deviceId, data);
    }

    public String getData(String deviceId) {
        return devices.get(deviceId);
    }

    public void deleteData(String deviceId) {
        devices.remove(deviceId);
    }
}
