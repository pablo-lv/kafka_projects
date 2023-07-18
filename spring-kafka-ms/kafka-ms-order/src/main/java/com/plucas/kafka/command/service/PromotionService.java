package com.plucas.kafka.command.service;

import com.plucas.kafka.api.request.PromotionRequest;
import com.plucas.kafka.command.action.PromotionAction;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    private PromotionAction action;

    public PromotionService(PromotionAction action) {
        this.action = action;
    }

    public void createPromotion(PromotionRequest request) {
        action.publishToKafka(request);
    }
}
