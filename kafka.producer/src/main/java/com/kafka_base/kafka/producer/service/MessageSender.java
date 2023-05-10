package com.kafka_base.kafka.producer.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageSender {

    KafkaTemplate<String, String> template;

    public String sendMessage(String topic, Integer partition, String message) throws ExecutionException, InterruptedException, TimeoutException {
        val future = template.send(topic, partition, "", message);
            val result = future.get(2, TimeUnit.SECONDS);
            log.info("Message successfully sent to topic {}, partition {}",
                    result.getProducerRecord().topic(),
                    result.getProducerRecord().partition());
            return "Message successfully sent";
    }
}
