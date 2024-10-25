package com.example.demo.service.device;

import org.springframework.stereotype.Service;

import java.util.*;


// FIXME maybe this class could be placed in repository

@Service
public class DeviceDataService {
    // TODO replaced with postgresql
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
