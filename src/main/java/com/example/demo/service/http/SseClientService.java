package com.example.demo.service.http;

import com.example.demo.event.ServerPushingEvent;
import com.example.demo.service.DeviceControlService;
import com.example.demo.service.ScheduledTask;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
public class SseClientService {
    private static final Logger log = LoggerFactory.getLogger(SseClientService.class);
    private static final String sseUrl = "http://47.108.27.238:80/api/sse";


    DeviceControlService deviceControlService;
    ApplicationEventPublisher publisher;
    ScheduledTask scheduledTask;
    ActixAuthService actixAuthService;
    WebClient webClient;

    public SseClientService(
            ApplicationEventPublisher publisher,
            DeviceControlService deviceControlService,
            ScheduledTask scheduledTask,
            ActixAuthService actixAuthService,
            WebClient webClient
    ) {
        this.publisher = publisher;
        this.deviceControlService = deviceControlService;
        this.scheduledTask = scheduledTask;
        this.actixAuthService = actixAuthService;
        this.webClient = webClient;
    }


//    @PostConstruct
//    public void connectToSse() {
//        new Thread(() -> {
//            while (true) {
//                try {
//                    handleSSE();
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e1) {
//                        log.error(e1.getMessage());
//                    }
//                }
//            }
//        }).start();
//    }
//
//    public void handleSSE() throws IOException {
//        log.info("Connecting to SSE Server");
//        URL url = new URL(sseUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "text/event-stream");
//
//
//        connection.setUseCaches(false);
//        connection.setDoInput(true);
//        connection.setDoOutput(false);
//
//        // 设置连接和读取超时
//        connection.setConnectTimeout(5000); // 5秒连接超时
//        connection.setReadTimeout(1000 * 60 * 5);    // 5分钟读取超时
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode != HttpURLConnection.HTTP_OK) {
//            throw new IOException("Failed to connect, HTTP response code: " + responseCode);
//        }
//        log.info("connected to SSE Server");
//        // 连接后即获取一次之前的任务
//        scheduledTask.performTask();
//
//        // 读取响应
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//            String line;
//            long lastHeartbeat = System.currentTimeMillis();
//
//            while ((line = reader.readLine()) != null) {
//                // 处理每一行数据
//                if (line.startsWith("data: ")) {
//                    String data = line.substring("data: ".length());
//                    log.info("Received SSE: {}, handle update", data);
//                    publishServerPushingEvent(data);
//
//                    // 重置心跳时间
//                    lastHeartbeat = System.currentTimeMillis();
//                }
//
//                // 心跳机制
//                if (System.currentTimeMillis() - lastHeartbeat > 30000) { // 每 30 秒发送一次心跳
//                    log.info("Sending heartbeat to SSE server");
//                    // 可以发送一个空消息或特定的心跳消息
//                    connection.getOutputStream().write(":\n\n".getBytes());
//                    connection.getOutputStream().flush();
//                    lastHeartbeat = System.currentTimeMillis();
//                }
//            }
//        } catch (IOException e) {
//            log.error("Error reading SSE response: {}", e.getMessage());
//            throw e; // 让上层处理重连
//        } finally {
//            connection.disconnect(); // 确保连接被断开
//        }
//    }

    @PostConstruct
    public void handle() {
        log.info("Starting SSE client request");
        webClient
                .get()
                .uri("/sse")
                .header("Accept", "text/event-stream")
                .header("Connection", "keep-alive")
                .header("Authorization", "Bearer " + actixAuthService.getToken())
                .retrieve()
                .bodyToFlux(String.class)
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof IOException || throwable instanceof TimeoutException) // 只针对特定异常重试
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new RuntimeException("Retries exhausted", retrySignal.failure()))
                )
                .onErrorResume(throwable -> {
                    log.error("Error while receiving SSE: {}, will retry", throwable.getMessage());
                    return Flux.empty(); // 可以在这里处理异常，比如返回一个空Flux，或触发重新订阅
                })
                .doOnNext(data -> {
                    log.info("Received SSE: {}, handle update", data);
                    publishServerPushingEvent(data); // 处理收到的数据
                })
                .subscribe();
        log.info("Finished SSE client request");
    }


    public void publishServerPushingEvent(final String data) {
        publisher.publishEvent(new ServerPushingEvent(this, data));
    }
}
