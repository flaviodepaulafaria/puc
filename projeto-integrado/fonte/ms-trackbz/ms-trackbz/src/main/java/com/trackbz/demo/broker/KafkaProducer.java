package com.trackbz.demo.broker;

import com.trackbz.demo.model.Gps;
import com.trackbz.demo.model.GpsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {
    private static final String TOPIC = "topic_localizacoes";

    @Autowired
    private KafkaTemplate<String, Gps> kafkaTemplate;

    public void sendMessage(GpsData data) {
        for (Gps gps:data.getList()) {
            kafkaTemplate.send(TOPIC, 0, String.valueOf(data.getKey()), gps);
        }
    }
}
