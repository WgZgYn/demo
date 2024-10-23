package com.example.demo.config;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Value("${mqtt-broker-url}")
    private String broker;

    @Value("${mqtt-username}")
    private String clientId;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(clientId);
        options.setServerURIs(new String[] { broker });
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);
//        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(90);
        return options;
    }

    @Bean
    public MqttAsyncClient mqttAsyncClient() throws MqttException {
        MqttConnectOptions options = mqttConnectOptions();
        MqttAsyncClient client = new MqttAsyncClient(broker, clientId, new MemoryPersistence());
        client.connect(options);
        return client;
    }
}
