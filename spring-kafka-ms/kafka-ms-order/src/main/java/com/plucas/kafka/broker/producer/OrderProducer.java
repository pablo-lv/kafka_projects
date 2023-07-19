package com.plucas.kafka.broker.producer;

import com.plucas.kafka.broker.message.OrderMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderMessage message) {
        var producerRecord = buildProducerRecord(message);

        CompletableFuture<SendResult<String, OrderMessage>> future = kafkaTemplate.send(producerRecord);
        future.whenCompleteAsync((result, ex) -> {
            if (ex == null) {
                LOG.info("Order {}, item {} published successfully", message.getOrderNumber(), message.getItemName());
            } else {
                LOG.info("Order {}, item {} failed to publish because {}", message.getOrderNumber(), message.getItemName(), ex.getMessage());
            }
        });

        LOG.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());
    }

    public ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
        var surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25: 15;
        var headers = new ArrayList<Header>();
        var surpriseBonusHeader = new RecordHeader("surpriseHeader", Integer.toString(surpriseBonus).getBytes());

        headers.add(surpriseBonusHeader);

        return new ProducerRecord<String, OrderMessage>("t-commodity-order", null, message.getOrderNumber(), message, headers);
    }
}
