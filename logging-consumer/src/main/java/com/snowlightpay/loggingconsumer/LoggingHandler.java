package com.snowlightpay.loggingconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoggingHandler {
    private final KafkaConsumer<String, String> kafkaConsumer;

    public LoggingHandler(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        this.consumer();
    }

    public void consumer() {
        Thread consumerThread = new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
                if (records == null) {
                    continue;
                }

                for (ConsumerRecord<String, String> record : records) {
                    handle(record);
                }
            }
        });
        consumerThread.start();
    }

    public void handle(ConsumerRecord<String, String> record) {
        if (!record.value().startsWith("[logging]")) {
            return;
        }

        System.out.println("Received message" + record.value());
    }
}
