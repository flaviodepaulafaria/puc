package com.trackbz.demo.controller;

import com.trackbz.demo.broker.KafkaProducer;
import com.trackbz.demo.model.GpsData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/gps")
public class GpsController {

    @Autowired
    private final KafkaProducer topicProducer;

    @PostMapping(value = "/send")
    public void send(@RequestBody GpsData data){
        topicProducer.sendMessage(data);
    }
}
