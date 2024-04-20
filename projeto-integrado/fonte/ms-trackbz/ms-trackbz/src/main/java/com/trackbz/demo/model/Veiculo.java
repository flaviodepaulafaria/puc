package com.trackbz.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("veiculos")
public class Veiculo {

    @Id
    @JsonProperty()
    private String id;
    @Field("veiculo_id")
    @JsonProperty("veiculo_id")
    private Integer veiculoId;
    @Field("nome_motorista")
    @JsonProperty("nome_motorista")
    private String nomeMotorista;

    public Veiculo(@JsonProperty("veiculo_id") Integer veiculoId, @JsonProperty("nome_motorista")String nomeMotorista) {
        this.veiculoId = veiculoId;
        this.nomeMotorista = nomeMotorista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Integer veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }
}
