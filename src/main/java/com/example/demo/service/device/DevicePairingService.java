package com.example.demo.service.device;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class DevicePairingService {
    @Value("${local_tcp_listening_port}")
    private int LISTEN_PORT;

    @Value("${pair_code}")
    private int ASK_FOR_PAIR;

    private final Set<String> pairingDevicesIP = new HashSet<>();

    DeviceControlService deviceControlService;
    DeviceDataService deviceDataService;

    public DevicePairingService(DeviceControlService deviceControlService, DeviceDataService deviceDataService) {
        this.deviceControlService = deviceControlService;
        this.deviceDataService = deviceDataService;
    }


    public void addPairingDevice(String ip) {
        pairingDevicesIP.add(ip);
    }

    public List<String> getPairingDevicesIP() {
        return pairingDevicesIP.stream().toList();
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
                        addPairingDevice(address.getHostAddress());
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
