package com.trackbz.demo.service;

import com.trackbz.demo.model.Veiculo;
import com.trackbz.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public void save(Veiculo veiculo) {
        repository.save(veiculo);
    }
    public List<Veiculo> findAll() {
        List<Veiculo> itemList = repository.findAll();
        itemList.forEach(item -> System.out.println(getItemDetails(item)));
        return itemList;
    }

    private String getItemDetails(Veiculo item) {

        System.out.println(
                "Item id: " + item.getVeiculoId() +
                        ", \nItem nome motorista: " + item.getNomeMotorista()
        );

        return "";
    }
}
