//package com.example.demo;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DeviceInfo {
//    public static class Func {
//        public String name;
//        Func(String name) {
//            this.name = name;
//        }
//    }
//
//    public String deviceId;
//    public String deviceName;
//    public String type;
//    public List<Func> funcs;
//
//    public DeviceInfo(String deviceId, String deviceName, String type) {
//        this.deviceId = deviceId;
//        this.deviceName = deviceName;
//        this.type = type;
//    }
//
//    public DeviceInfo(String deviceId, String deviceName, String type, List<Func> funcs) {
//        this.deviceId = deviceId;
//        this.deviceName = deviceName;
//        this.type = type;
//        this.funcs = funcs;
//    }
//
//    @Override
//    public String toString() {
//        return "DeviceInfo [deviceId=" + deviceId + ", deviceName=" + deviceName + ", type=" + type + "]" ;
//    }
//}
//
//
//@Controller
//class DeviceInfoController {
//    @GetMapping("/deviceinfo")
//    @ResponseBody
//    public DeviceInfo deviceInfo() {
//        List<DeviceInfo.Func> f =  new ArrayList<>()
//        {{
//            add(new DeviceInfo.Func("open"));
//            add(new DeviceInfo.Func("close"));
//        }};
//        return new DeviceInfo("0", "ESP32", "switch", f);
//    }
//}