package com.snowlightpay.money.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CountDownLatchManager;
import com.snowlightpay.common.LoggingProducer;
import com.snowlightpay.common.RechargingMoneyTask;
import com.snowlightpay.common.SubTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class TaskResultConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final CountDownLatchManager countDownLatchManager;
    private final LoggingProducer loggingProducer;

    public TaskResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                              @Value("${task.result.topic}") String topic,
                              CountDownLatchManager countDownLatchManager, LoggingProducer loggingProducer) {
        this.countDownLatchManager = countDownLatchManager;
        this.loggingProducer = loggingProducer;
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                    RechargingMoneyTask task;
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Receive Message: " + record.key() + "/ " + record.value());

                        try {
                            task = objectMapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        boolean taskResult = true;
                        for (SubTask subTask: task.getSubTaskList()) {
                            // what subtask membership banking
                            // external port adapter

                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;
                                break;
                            }
                        }

                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.getTaskID(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "success");
                        } else {
                            this.loggingProducer.sendMessage(task.getTaskID(), "task fail");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "fail");
                        }

//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }

                        this.countDownLatchManager.getCountDownLatch(task.getTaskID()).countDown();
                    }
                }
            } finally {
                consumer.close();
            }
        });

        consumerThread.start();
    }
}
