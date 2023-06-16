package com.plucas.kafka;

import com.plucas.kafka.producer.HelloKafkaProducer;
import com.plucas.kafka.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaKeyProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaCoreProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		producer.sendingHello("Plucas " + ThreadLocalRandom.current().nextInt());
		for (int i = 0; i < 30; i++) {
			var key = "Key-" + (i %4);
			var value = "value " + i + " with key " + key;
			producer.send(key, value);
		}
	}
}
