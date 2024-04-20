package com.trackbz.demo;

import com.trackbz.demo.broker.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class MsTrackbzApplication {

    private KafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(MsTrackbzApplication.class, args);

    }

    @Autowired
    MsTrackbzApplication(KafkaProducer producer) {
        this.producer = producer;
    }

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

}
