package com.plucas.kafka.broker.consumer;

import com.plucas.kafka.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PromotionUppercaseConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(PromotionUppercaseConsumer.class);

    public void listenPromotionUppercase(PromotionMessage message) {
        LOG.info("Processing uppercase promotion : {}", message);
    }
}
