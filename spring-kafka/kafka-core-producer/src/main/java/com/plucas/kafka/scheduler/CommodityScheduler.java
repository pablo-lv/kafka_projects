package com.plucas.kafka.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.plucas.kafka.entity.Commodity;
import com.plucas.kafka.producer.CommodityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class CommodityScheduler {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer producer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities() throws JsonProcessingException {
        var commodities = restTemplate.exchange("http://localhost:8080/v1/api/commodity/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();

        CommodityProducer commodityProducer = producer;
        for (Commodity commodity : commodities) {
            commodityProducer.sendMessage(commodity);
        }
    }
}
