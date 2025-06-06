package com.hernandezedwin.LiterBook.repository;

import com.hernandezedwin.LiterBook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTitulo(String nombreLibro);
    List<Libro> findAll();
}
