package com.hernandezedwin.LiterBook.repository;

import com.hernandezedwin.LiterBook.model.Autor;
import com.hernandezedwin.LiterBook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTitulo(String nombreLibro);
    List<Libro> findAll();
    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE " +
            "(cast(a.FechaNacimiento as integer)>= :fechaInicio AND (a.FechaMuerte IS NULL OR cast(a.FechaMuerte as integer) <= :fechaFin))")
    List<Libro> listarLibroPoAutorVivo(Integer fechaInicio, Integer fechaFin);
}
