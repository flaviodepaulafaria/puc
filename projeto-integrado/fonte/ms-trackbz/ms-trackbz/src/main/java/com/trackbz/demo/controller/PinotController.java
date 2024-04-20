package com.trackbz.demo.controller;

import com.trackbz.demo.service.PinotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/queries")
public class PinotController {


    @Autowired
    private PinotService service;
    @GetMapping("/{id}")
    public void queryById(@PathVariable("id") Integer id){
        service.queryData(id);
    }


}
