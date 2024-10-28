package com.example.demo.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MqttClientConfig {
    private final MqttConfig.Client mqttConstant;

    MqttClientConfig(MqttConfig.Client mqttConstant) {
        this.mqttConstant = mqttConstant;
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttConstant.clientId);
        options.setServerURIs(new String[]{mqttConstant.broker});

        options.setAutomaticReconnect(true);
        options.setCleanSession(false);
        options.setKeepAliveInterval(90);
        return options;
    }

    @Bean
    public MqttClient mqttClient() throws MqttException {
        return new MqttClient(mqttConstant.broker, mqttConstant.clientId, new MemoryPersistence());
    }
}
