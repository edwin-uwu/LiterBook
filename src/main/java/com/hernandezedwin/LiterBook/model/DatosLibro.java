package com.hernandezedwin.LiterBook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        ArrayList<DatosAutor> autor,
        @JsonAlias("languages")
        List<String> idioma,
        @JsonAlias("download_count")
        Long numeroDeDescargas
) {
}
