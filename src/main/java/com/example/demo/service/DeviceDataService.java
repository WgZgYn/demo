package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceDataService {
    private final Map<String, String> devices = new HashMap<>();

    private final Set<String> pairing_devices = new HashSet<>();

    public void addPairingDevice(String ip) {
        pairing_devices.add(ip);
    }

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
