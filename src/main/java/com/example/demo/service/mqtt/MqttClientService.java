package com.example.demo.service.mqtt;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Mqtt服务
 */
@Service
public class MqttClientService {
    private static final String broker = "tcp://47.108.27.238:1883";
    private static final Logger log = LoggerFactory.getLogger(MqttClientService.class);
    private static final String clientId = "wzy-host";
    private final MqttConnectOptions options;

    ApplicationEventPublisher publisher;
    MqttClient client = null;

    MqttClientService(ApplicationEventPublisher publisher, MqttConnectOptions options) {
        this.publisher = publisher;
        this.options = options;
    }

    @PostConstruct
    void init() {
        try {
            connect();
        } catch (MqttException e) {
            log.error(e.getMessage());
        }
    }


    public void connect() throws MqttException {
        this.client = new MqttClient(broker, clientId, new MemoryPersistence());
        client.setCallback(new Callback());
        client.connect(options);
        client.subscribe("host");
        log.info("Connected to " + broker);
    }

    public void disconnect() throws MqttException {
        if (client.isConnected()) {
            client.disconnect();
        }
    }

    public void publish(String topic, String message) throws MqttException {
        client.publish(topic, new MqttMessage(message.getBytes()));
    }

    class Callback implements MqttCallback, MqttCallbackExtended {
        @Override
        public void connectionLost(Throwable throwable) {
            log.info("MqttClient Connection lost");
        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) {
            log.info("MqttMessage received: {}", mqttMessage.toString());
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            log.info("Delivery complete");
        }

        @Override
        public void connectComplete(boolean b, String s) {
            try {
                client.subscribe("host");
                log.info("Reconnect and subscribe success");
            } catch (MqttException e) {
                log.error(e.getMessage());
            }
        }
    }
}
