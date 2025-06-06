package com.hernandezedwin.LiterBook.repository;

import com.hernandezedwin.LiterBook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    public Optional<Libro> findByTitulo(String nombreLibro);
}
