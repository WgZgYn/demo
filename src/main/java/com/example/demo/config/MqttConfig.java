package com.example.demo.config;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "mqtt-client")
public class MqttConfig {
    private String broker;
    private String username;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
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
        MqttAsyncClient client = new MqttAsyncClient(broker, username, new MemoryPersistence());
        client.connect(options);
        return client;
    }
}
