package com.hernandezedwin.LiterBook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "idiomas")
public class Lenguaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lenguaje;
    @ManyToOne
    private Libro libro;

    public Lenguaje(){}
    public Lenguaje(String datosLenguaje, Libro libro){
        this.lenguaje = datosLenguaje;
        this.libro = libro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
