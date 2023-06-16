package com.plucas.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaKeyProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaKeyProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String key, String value) {
        kafkaTemplate.send("t-multi-partitions", key, value);
    }
}
