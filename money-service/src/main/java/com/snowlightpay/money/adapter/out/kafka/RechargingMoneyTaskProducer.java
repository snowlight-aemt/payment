package com.snowlightpay.money.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.RechargingMoneyTask;
import com.snowlightpay.money.application.port.out.SendRechargingMoneyTaskPort;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class RechargingMoneyTaskProducer implements SendRechargingMoneyTaskPort {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    public RechargingMoneyTaskProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                       @Value("${task.topic}") String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    @Override
    public void SendRechargingMoneyTaskPort(RechargingMoneyTask task) {
        sendMessage(task.getTaskID(), task);
    }

    private void sendMessage(String key, RechargingMoneyTask task) {
        ObjectMapper objectMapper = new ObjectMapper();
        String rechargingMoneyTaskJson;
        try {
            rechargingMoneyTaskJson= objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, rechargingMoneyTaskJson);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
//                System.out.println("Message sent successfully. Offset: " + metadata.offset());
            } else {
                exception.printStackTrace();
//                System.out.println("Failed to send message: " + exception.getMessage());
            }
        });
    }
}
