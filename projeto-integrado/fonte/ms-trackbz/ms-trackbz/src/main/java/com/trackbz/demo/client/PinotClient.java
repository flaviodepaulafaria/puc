package com.trackbz.demo.client;

import com.trackbz.demo.model.PinotResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "idPinotClient", url = "${pinot.url}")
public interface PinotClient {

    @PostMapping(value = "/query/sql")
    PinotResponse query(@RequestHeader Map<String, String> header, String body);

}
