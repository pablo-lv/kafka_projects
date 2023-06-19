package com.plucas.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plucas.kafka.entity.Commodity;
import com.plucas.kafka.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CommodityDashboardConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(FixedRateConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
    public void listen(String message) throws JsonProcessingException {
        var commodity = objectMapper.readValue(message, Commodity.class);
        LOG.info("Dashboard logic for : {}", commodity);
    }

}
