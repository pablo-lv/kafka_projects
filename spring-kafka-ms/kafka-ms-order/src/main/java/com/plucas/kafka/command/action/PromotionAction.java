package com.plucas.kafka.command.action;

import com.plucas.kafka.api.request.PromotionRequest;
import com.plucas.kafka.broker.message.PromotionMessage;
import com.plucas.kafka.broker.producer.PromotionProducer;
import org.springframework.stereotype.Component;

@Component
public class PromotionAction {

    private PromotionProducer producer;

    public PromotionAction(PromotionProducer producer) {
        this.producer = producer;
    }

    public void publishToKafka(PromotionRequest request) {
        var message = new PromotionMessage(request.getPromotionCode());

        producer.publish(message);
    }
}
