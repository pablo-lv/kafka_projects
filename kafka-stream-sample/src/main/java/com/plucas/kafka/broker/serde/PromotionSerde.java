package com.plucas.kafka.broker.serde;

import com.plucas.kafka.broker.message.PromotionMessage;

public class PromotionSerde extends CustomJsonSerde<PromotionMessage> {

    public PromotionSerde() {
        super(new CustomJsonSerializer<PromotionMessage>(),
                new CustomJsonDeserializer<PromotionMessage>(PromotionMessage.class));
    }
}
