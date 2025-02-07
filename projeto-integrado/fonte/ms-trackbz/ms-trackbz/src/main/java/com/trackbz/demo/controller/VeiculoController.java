package com.trackbz.demo.controller;

import com.trackbz.demo.model.Veiculo;
import com.trackbz.demo.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/veiculos")
public class VeiculoController {


    @Autowired
    private VeiculoService service;
    @PostMapping
    public void post(@RequestBody Veiculo veiculo){
        service.save(veiculo);
    }
    @GetMapping
    public List<Veiculo> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id)")
    public Veiculo findById(@PathVariable Long id){
        return service.findById(id);
    




}
