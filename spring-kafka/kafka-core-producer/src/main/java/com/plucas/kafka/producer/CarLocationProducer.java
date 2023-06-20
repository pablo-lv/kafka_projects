package com.plucas.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plucas.kafka.entity.CarLocation;
import com.plucas.kafka.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarLocationProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(CarLocation carLocation) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(carLocation);
        kafkaTemplate.send("t-location", json);
    }
}
