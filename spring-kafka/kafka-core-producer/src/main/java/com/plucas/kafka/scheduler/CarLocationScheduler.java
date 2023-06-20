package com.plucas.kafka.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.plucas.kafka.entity.CarLocation;
import com.plucas.kafka.entity.Commodity;
import com.plucas.kafka.producer.CarLocationProducer;
import com.plucas.kafka.producer.CommodityProducer;
import com.plucas.kafka.producer.FixedRateProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CarLocationScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(FixedRateProducer.class);

    private CarLocation carOne;
    private CarLocation carTwo;
    private CarLocation carThree;

    @Autowired
    private CarLocationProducer producer;

    public CarLocationScheduler() {
        var now = System.currentTimeMillis();

        carOne = new CarLocation("car-one", now, 0);
        carTwo = new CarLocation("car-two", now, 110);
        carThree = new CarLocation("car-three", now, 95);
    }
    @Scheduled(fixedRate = 10000)
    public void generateCarLocation() throws JsonProcessingException {
        var now = System.currentTimeMillis();

        carOne.setTimestamp(now);
        carTwo.setTimestamp(now);
        carThree.setTimestamp(now);

        carOne.setDistance(carOne.getDistance() + 1);
        carTwo.setDistance(carTwo.getDistance() - 1);
        carThree.setDistance(carThree.getDistance() + 1);

        producer.sendMessage(carOne);
        producer.sendMessage(carTwo);
        producer.sendMessage(carThree);

        LOG.info("Snt : {}", carOne);
        LOG.info("Snt : {}", carTwo);
        LOG.info("Snt : {}", carThree);
    }
}
