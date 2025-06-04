package com.hernandezedwin.LiterBook.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApiService {
    public String DatosAPI(String url){
        HttpClient cliente =HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> respuesta = null;
        try
        {
            respuesta = cliente.send(solicitud,HttpResponse.BodyHandlers.ofString());
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
        String json =respuesta.body();
        return json;
    }
}
