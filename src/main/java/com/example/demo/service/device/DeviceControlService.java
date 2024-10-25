package com.example.demo.service.device;

import com.example.demo.service.mqtt.MqttClientService;
import com.example.demo.util.TcpStream;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DeviceControlService {
    private static final int DEFAULT_TIMEOUT = 5000;

    @Value("${device_tcp_listening_port}")
    private int PORT;

    @Value("${using-tcp}")
    private static boolean usingTcp;

    MqttClientService mqttClientService;
    DeviceDataService deviceDataService;

    public DeviceControlService(DeviceDataService deviceDataService, MqttClientService mqttClientService) {
        this.deviceDataService = deviceDataService;
        this.mqttClientService = mqttClientService;
    }

    public boolean deviceOps(String device, String ops) {
        log.info("device {} {}", device, ops);

        if (usingTcp) {
            try {
                if (!deviceDataService.hasDevice(device)) {
                    return false;
                }
                String status = TcpStream.sendAndReceive(deviceDataService.getData(device), PORT, DEFAULT_TIMEOUT, ops.getBytes());
                return true;
            } catch (IOException e) {
                e.fillInStackTrace();
                log.error(e.getMessage());
            }
        } else {
            try {
                mqttClientService.publish(device + "/service", ops);
                return true;
            } catch (MqttException e) {
                e.fillInStackTrace();
                log.error(e.getMessage());
            }
        }
        return false;
    }

    /*JUST FOR TEST*/
    public void sendMqttMessage() throws MqttException {
        mqttClientService.publish("test/lol", "switch");
    }

    public boolean connect(String ip) {
        log.info("device connect {}", ip);
        try {
            String device_id = TcpStream.sendAndReceive(ip, PORT, DEFAULT_TIMEOUT, "?deviceId".getBytes());
            deviceDataService.addDevice(device_id, ip);
            return true;
        } catch (IOException e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public void send_event(String device, String action) {
        deviceOps(device, action);
    }
}
