package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


@Service
public class DevicePairService {
    private static final Logger log = LoggerFactory.getLogger(DevicePairService.class);
    private static final int LISTEN_PORT = 45677;
    DeviceControlService deviceControlService;
    DeviceDataService deviceDataService;

    public DevicePairService(DeviceControlService deviceControlService, DeviceDataService deviceDataService) {
        this.deviceControlService = deviceControlService;
        this.deviceDataService = deviceDataService;
    }


    @PostConstruct
    public void startTcpServer() {
        Thread tcpServer = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(LISTEN_PORT)) {
                log.info("Listening on port {}", LISTEN_PORT);
                while (true) {
                    try (Socket clientSocket = serverSocket.accept()) {
                        InetAddress address = clientSocket.getInetAddress();
                        log.info("the device's ip: {}", address.getHostAddress());
                        String deviceId = new String(clientSocket.getInputStream().readAllBytes());
                        clientSocket.getOutputStream().write(200);
                        log.info("read reply: {}", deviceId);
                        deviceDataService.addDevice(deviceId, address.getHostAddress());
                    } catch (IOException e) {
                        log.error("Client connection error: ", e);
                    }
                }
            } catch (IOException e) {
                System.err.println("Could not start server: " + e.getMessage());
            }
        });
        tcpServer.start();
    }
}
