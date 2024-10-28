package com.example.demo.service.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Device;
import com.example.demo.repository.DeviceRepository;


// FIXME maybe this class could be placed in repository

@Service
public class DeviceDataService {
    // TODO replaced with postgresql
    //private final Map<String, String> devices = new HashMap<>();
    @Autowired
    private DeviceRepository deviceRepository;

    // public List<String> listDevices() {
    //   return new ArrayList<>(devices.keySet());
    // }
    public List<Device> listDevices() {
        return deviceRepository.findAll();
    }

    // public boolean hasDevice(String deviceId) {
    //     return devices.containsKey(deviceId);
    // }
    public boolean hasDevice(Integer deviceId) {
        return deviceRepository.existsById(deviceId);
    }

    // public void addDevice(String deviceId, String data) {
    //     devices.put(deviceId, data);
    // }
    public void addDevice(Device device) {
        deviceRepository.save(device);
    }

    // public String getData(String deviceId) {
    //     return devices.get(deviceId);
    // }
    public Device getData(Integer deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }


    // public void deleteData(String deviceId) {
    //     devices.remove(deviceId);
    // }
    public void deleteData(Integer deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
