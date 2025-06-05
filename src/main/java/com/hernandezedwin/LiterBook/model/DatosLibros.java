package com.hernandezedwin.LiterBook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("count")
        Long numeroDeLibros,
        @JsonAlias("results")
        List<DatosLibro> libro
) {
}
