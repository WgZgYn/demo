package com.example.demo.service.mqtt;

import com.example.demo.config.MqttConfig;
import com.example.demo.dto.MqttEventMessage;
import com.example.demo.event.events.DeviceEvent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Mqtt服务
 */
@Slf4j
@Service
public class MqttClientService {
    ApplicationEventPublisher publisher;
    MqttClient client;
    MqttConnectOptions options;
    MqttConfig.Topic mqttTopic;

    MqttClientService(ApplicationEventPublisher publisher, MqttConnectOptions options, MqttClient client, MqttConfig.Topic mqttTopic) {
        this.publisher = publisher;
        this.options = options;
        this.client = client;
        this.mqttTopic = mqttTopic;
    }

    @PostConstruct
    void init() {
        try {
            client.setCallback(new Callback());
            client.connect(options);
            setSubscribedTopics();
            log.info("mqtt client init finished");
        } catch (MqttException e) {
            log.error("mqtt client init error", e);
        }
    }

    void setSubscribedTopics() throws MqttException {
        client.subscribe(mqttTopic.getSubscribeTopics(), mqttTopic.getSubscribeQos());
    }

    public void disconnect() throws MqttException {
        if (client.isConnected()) {
            client.disconnect();
        }
    }

    public void publish(String topic, String message) throws MqttException {
        publish(topic, message, 2);
    }


    public void publish(String topic, String message, int qos) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qos);
        client.publish(topic, mqttMessage);
    }


    class Callback implements MqttCallback, MqttCallbackExtended {
        @Override
        public void connectionLost(Throwable throwable) {
            log.info("MqttClient Connection lost");
        }

        @Override
        public void messageArrived(String topic, MqttMessage mqttMessage) {
            log.info("MqttMessage received: {}", mqttMessage.toString());
            if (topic.equals(mqttTopic.getDeviceEvents())) {
                byte[] data = mqttMessage.getPayload();
                try {
                    MqttEventMessage msg = MqttEventMessage.from(data);
                    publisher.publishEvent(new DeviceEvent(this, msg.getFrom(), msg.getEvent()));
                } catch (MqttEventMessage.Error e) {
                    log.error(e.getMessage());
                }
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            log.info("Delivery complete");
        }

        @Override
        public void connectComplete(boolean b, String s) {
            try {
                setSubscribedTopics();
                log.info("Reconnect and subscribe success");
            } catch (MqttException e) {
                log.error(e.getMessage());
            }
        }
    }
}
