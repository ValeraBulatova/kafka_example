package com.kafka_base.kafka.producer.controller;

import com.kafka_base.kafka.producer.model.Message;
import com.kafka_base.kafka.producer.service.MessageSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class SenderController {

    MessageSender messageSender;

    @PostMapping("/send/{topic}")
    public ResponseEntity<String> sendMessage(@PathVariable String topic,
                                              @RequestParam(value = "part", required = false) Integer partition,
                                              @RequestBody Message message) {
        try {
            return new ResponseEntity<>(messageSender.sendMessage(topic, partition, message.getMessageText()), OK);
        } catch (TimeoutException | ExecutionException | InterruptedException e) {
            return new ResponseEntity<>(String.format("Cannot send message to Kafka Topic %s", topic), INTERNAL_SERVER_ERROR);
        }
    }

}
