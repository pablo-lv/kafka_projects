package com.plucas.kafka.broker.producer;

import com.plucas.kafka.broker.message.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderMessage message) {
        CompletableFuture<SendResult<String, OrderMessage>> future = kafkaTemplate.send("t-commodity-order", message.getOrderNumber(), message);
        future.whenCompleteAsync((result, ex) -> {
            if (ex == null) {
                LOG.info("Order {}, item {} published successfully", message.getOrderNumber(), message.getItemName());
            } else {
                LOG.info("Order {}, item {} failed to publish because {}", message.getOrderNumber(), message.getItemName(), ex.getMessage());
            }
        });

        LOG.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());
    }
}
