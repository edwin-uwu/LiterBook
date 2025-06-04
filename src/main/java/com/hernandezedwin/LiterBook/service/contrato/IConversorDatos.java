package com.hernandezedwin.LiterBook.service.contrato;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
