package com.hernandezedwin.LiterBook.dto;

public record AutorDTO(
        Long id,
        String nombre,
        String fechaNacimiento,
        String fechaMuerte
) {
}
