//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class DeviceInfoGetController {
//    @Autowired
//    DeviceInfoService deviceInfoService;
//
//    @GetMapping("/deviceOps")
//    public void deviceOps() {
//        DeviceInfo a = new DeviceInfo("1", "ESP32", "Switch", null);
//        deviceInfoService.addDeviceInfo(a);
//        DeviceInfo l = deviceInfoService.getDeviceInfo("0");
//        System.out.println(l);
//
//        List<DeviceInfo> list = deviceInfoService.getAllDeviceInfo();
//        for (DeviceInfo deviceInfo : list) {
//            System.out.println(deviceInfo);
//        }
//    }
//
//    @GetMapping("/device/{id}")
//    public DeviceInfo device(@PathVariable String id) {
//        return deviceInfoService.getDeviceInfo(id);
//    }
//}
