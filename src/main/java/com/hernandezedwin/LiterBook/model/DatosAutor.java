package com.hernandezedwin.LiterBook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        String FechaNacimiento,
        @JsonAlias("death_year")
        String FechaMuerte
) {
}
