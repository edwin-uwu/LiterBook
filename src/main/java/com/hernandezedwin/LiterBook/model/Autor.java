package com.hernandezedwin.LiterBook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String FechaNacimiento;
    private String FechaMuerte;
    @ManyToOne
    private Libro libro;

    public Autor(){}
    public Autor(DatosAutor datosAutor, Libro libro){
        this.nombre = datosAutor.nombre();
        this.FechaNacimiento = datosAutor.FechaNacimiento();
        this.FechaMuerte = datosAutor.FechaMuerte();
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public String getFechaMuerte() {
        return FechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        FechaMuerte = fechaMuerte;
    }
}
