package com.kafka_base.kafka.producer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private String messageText;
}
