package com.snowlightpay.loggingconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoggingHandlerTest {

    @InjectMocks
    LoggingHandler loggingHandler;

    @Mock
    KafkaConsumer<String, String> kafkaConsumer;

    @BeforeEach
    void setup() {
        loggingHandler = new LoggingHandler(kafkaConsumer);
    }

    private static Stream<String> provideStringForConsumer() {
        return Stream.of(
                new String("[log] hello world"),
                new String("[logging] hello world")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringForConsumer")
    public void consumer(String dummyString) throws Exception {
        when(kafkaConsumer.poll(Duration.ofSeconds(1)))
                .thenReturn(createDummyRecords(dummyString));
    }

    public ConsumerRecords<String, String> createDummyRecords(String consumerString) {
        List list = new ArrayList<>();
        ConsumerRecord<String, String> dummyRecode = new ConsumerRecord<>("test", 0, 0, "key", consumerString);
        list.add(dummyRecode);

        HashMap<TopicPartition, List<ConsumerRecord<String, String>>> map = new HashMap<>();
        map.put(new TopicPartition("test", 0), list);
        return new ConsumerRecords<>(map);
    }
}