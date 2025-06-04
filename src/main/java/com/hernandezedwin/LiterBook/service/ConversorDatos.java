package com.hernandezedwin.LiterBook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hernandezedwin.LiterBook.service.contrato.IConversorDatos;

public class ConversorDatos implements IConversorDatos {
    private ObjectMapper objectMapper =new ObjectMapper();
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try{
return  objectMapper.readValue(json,clase);
        }catch(JsonProcessingException ex){
            throw new RuntimeException(ex);
        }
    }
}
