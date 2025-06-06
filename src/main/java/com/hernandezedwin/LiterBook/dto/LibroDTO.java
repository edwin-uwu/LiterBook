package com.hernandezedwin.LiterBook.dto;

public record LibroDTO(
        Long id,
        String titulo,
        String autores,
        String idiomas,
        Long descargas
) {
}
