package com.example.demo.service.tcp;

import com.example.demo.service.DeviceControlService;
import com.example.demo.service.DeviceDataService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


@Slf4j
@Service
public class DevicePairService {
    @Value("${local_tcp_listening_port}")
    private int LISTEN_PORT;

    @Value("${pair_code}")
    private int ASK_FOR_PAIR;

    DeviceControlService deviceControlService;
    DeviceDataService deviceDataService;

    public DevicePairService(DeviceControlService deviceControlService, DeviceDataService deviceDataService) {
        this.deviceControlService = deviceControlService;
        this.deviceDataService = deviceDataService;
    }

    @PostConstruct
    public void startTcpServer() {

        // TODO: refactor the dto
        Thread tcpServer = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(LISTEN_PORT)) {
                log.info("Listening on port {}", LISTEN_PORT);
                while (true) {
                    try (Socket clientSocket = serverSocket.accept()) {
                        InetAddress address = clientSocket.getInetAddress();
                        log.info("the device's ip: {}", address.getHostAddress());

//                        String deviceId = new String(clientSocket.getInputStream().readAllBytes());
                        if (clientSocket.getInputStream().read() != ASK_FOR_PAIR) {
                            continue;
                        }

                        clientSocket.getOutputStream().write(200);
//                        log.info("read reply: {}", deviceId);
                        deviceDataService.addPairingDevice(address.getHostAddress());
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
