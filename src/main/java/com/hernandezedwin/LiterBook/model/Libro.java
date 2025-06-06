package com.hernandezedwin.LiterBook.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToMany(mappedBy = "libro",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Autor> autores;
    @OneToMany(mappedBy = "libro",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Lenguaje> idioma;
    private Long numeroDeDescargas;

    public Libro(){}
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autor().stream()
                .map(a -> new Autor(a,this))
                .collect(Collectors.toList());
        this.idioma = datosLibro.idioma().stream()
                .map(i-> new Lenguaje(i,this))
                .collect(Collectors.toList());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Lenguaje> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<Lenguaje> idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
