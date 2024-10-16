package com.example.demo.service.mdns;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

@Slf4j
@Service
public class mdnsService {
    private JmDNS jmdns;

    @PostConstruct
    public void init() {
        try {
            // 获取本地 IP 地址
            InetAddress localHost = InetAddress.getLocalHost();
            jmdns = JmDNS.create(localHost);

            // 创建服务信息
            String serviceName = "MyService";
            String serviceType = "_http._tcp.local.";
            int port = 8080; // 服务端口
            String serviceDescription = "My Spring Boot mDNS Service";

            ServiceInfo serviceInfo = ServiceInfo.create(
                    serviceType,
                    serviceName,
                    port,
                    serviceDescription
            );

            // 注册服务
            jmdns.registerService(serviceInfo);
            System.out.println("mDNS service registered: " + serviceName);
        } catch (IOException e) {
            e.fillInStackTrace();
            log.error(e.getMessage());
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (jmdns != null) {
                jmdns.unregisterAllServices();
                jmdns.close();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
            log.error(e.getMessage());
        }
    }
}
