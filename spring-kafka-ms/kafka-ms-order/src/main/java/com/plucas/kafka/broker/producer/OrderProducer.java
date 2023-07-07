package com.plucas.kafka.broker.producer;

import com.plucas.kafka.broker.message.OrderMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderMessage message) {
        kafkaTemplate.send("t-commodity-order", message.getOrderNumber(), message);
    }
}
