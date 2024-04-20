package com.trackbz.demo.service;

import com.trackbz.demo.client.PinotClient;
import com.trackbz.demo.model.PinotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class PinotService {


    @Autowired
    PinotClient client;

    public void queryData(Integer veiculoId){
        HashMap<String, String> requestHeader = new HashMap<>();
        requestHeader.put("Authorization", "Bearer st-JhX7W0ZLBHWL3Y9k-UJVwvgAwbMr27anQDAmyxlexIrKZLC4t");
        String body = "{\"sql\":\"select VEICULO_ID, MOTORISTA, LATITUDE, LONGITUDE, DATAHORA from localizacoes where VEICULO_ID = " + veiculoId + " limit 500\"}";
        PinotResponse response = client.query(requestHeader, body);
        export(response);
    }

    private void export(PinotResponse response) {
        if(Objects.nonNull(response)) {
            String cabecalho = response.getResultTable().getDataSchema().getColumnNames().toString();
            String linhas = "";
            for (List<Object> rows : response.getResultTable().getRows()) {
                String l = "";
                for (int i = 0; i < rows.size(); i++) {
                    Object r = rows.get(i);

                    l = l + r;

                    if (i < 5)
                        l = l + ",";

                }
                linhas = linhas + l + "\n";
            }

            try {
                String file = cabecalho.replace("[", "").replace("]", "") + "\n" + linhas;
                FileOutputStream outputStream = new FileOutputStream("C:\\FPF\\Pessoal\\PUC\\projeto integrado\\projetos\\trackBZ\\arquivos\\export.csv");
                byte[] strToBytes = file.getBytes();
                outputStream.write(strToBytes);
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao exportar arquivo pinot ", e);
            }
        }
    }

}
