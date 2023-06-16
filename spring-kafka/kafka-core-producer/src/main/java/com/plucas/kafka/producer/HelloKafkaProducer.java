package com.plucas.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaProducer {

    private KafkaTemplate<String, String> kafKtKafkaTemplate;

    public HelloKafkaProducer(KafkaTemplate<String, String> kafKtKafkaTemplate) {
        this.kafKtKafkaTemplate = kafKtKafkaTemplate;
    }

    public void sendingHello(String name) {
        kafKtKafkaTemplate.send("t-hello", "Hello " + name);
    }
}
