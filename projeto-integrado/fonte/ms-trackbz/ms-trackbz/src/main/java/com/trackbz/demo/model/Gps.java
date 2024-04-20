package com.trackbz.demo.model;


public class Gps {

    private Integer veiculo_id;

    private Localizacao localizacao;
    private Long datahora;

    public Integer getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Integer veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Long getDatahora() {
        return datahora;
    }

    public void setDatahora(Long datahora) {
        this.datahora = datahora;
    }
}
