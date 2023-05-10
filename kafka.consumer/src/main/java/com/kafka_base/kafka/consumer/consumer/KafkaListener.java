package com.kafka_base.kafka.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.*;
import org.springframework.messaging.handler.annotation.*;

@Slf4j
@Service
public class KafkaListener {

    public static final String TOPIC_NAME = "kafka.topic";

    @org.springframework.kafka.annotation.KafkaListener(topics = {"kft", "kft.partitions"})
    public void consumeWithPartitions(@Payload String message,
                                      @Header(KafkaHeaders.RECEIVED_TOPIC)  String topic,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                                      @Header(KafkaHeaders.OFFSET) String offset) {
        log.info("New message received to topic: {}, partition: {}, offset: {} message text: {}",
                topic, partition, offset, message);
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = TOPIC_NAME)
    public void consume() {
        log.info("New message received to topic {}", TOPIC_NAME);
    }
}
