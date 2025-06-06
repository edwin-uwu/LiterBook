package com.hernandezedwin.LiterBook.principal;

import com.hernandezedwin.LiterBook.dto.LibroDTO;
import com.hernandezedwin.LiterBook.model.DatosAutor;
import com.hernandezedwin.LiterBook.model.DatosLibro;
import com.hernandezedwin.LiterBook.model.DatosLibros;
import com.hernandezedwin.LiterBook.model.Libro;
import com.hernandezedwin.LiterBook.repository.LibroRepository;
import com.hernandezedwin.LiterBook.service.ConsumoApiService;
import com.hernandezedwin.LiterBook.service.ConversorDatos;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.PSource;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/";
    private ConsumoApiService api= new ConsumoApiService();
    private ConversorDatos conversor = new ConversorDatos();
    @Autowired
    private LibroRepository repository;

    public Principal(LibroRepository repository){
        this.repository = repository;
    }

    public void menu()
    {
        int opcion = 1;
        while(opcion != 0){
            System.out.println("""
                \t\tMenu
                1- Buscar libro por titulo
                2- Lista de libros
                0- Salir
                """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listaDeLibros();
                    break;
                case 0:
                    System.out.println("Cerrando ...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    public void mostrarPrincipal(){
        System.out.println("Libros");
        var json =api.DatosAPI(URL);
        System.out.println(json);
        var datos =conversor.obtenerDatos(json, DatosLibros.class);
        System.out.println(datos);
    }
    private void buscarLibroPorTitulo() {
        System.out.println("Ingresa el título del libro que desea buscar");
        var tituloLibro = teclado.nextLine();

        var json = api.DatosAPI(URL + "?search=" + tituloLibro.replace(" ","%20"));
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosLibros.class);
        if(datos != null && !datos.libro().isEmpty()){
            //System.out.printf(datos.libro().get(0).titulo().toString());
            System.out.printf("""
                **** Libro ****
                Título: %s
                Autor: %s
                Idiomas: %s
                Descargas: %s
                \n
                """,
                    datos.libro().get(0).titulo(),
                    datos.libro().get(0).autor()
                            .stream().map(DatosAutor::nombre)
                            .collect(Collectors.joining()),
                    datos.libro().get(0).idioma(),
                    datos.libro().get(0).numeroDeDescargas());
            Libro libroEncontrado = new Libro(datos.libro().get(0));

            Optional<Libro> tituloEncontrado = repository.findByTitulo(datos.libro().get(0).titulo());
            if(!tituloEncontrado.isPresent()){
                repository.save(libroEncontrado);
            }else{
                System.out.println("Titulo encontrado en base de datos");
            }

        }else{
            System.out.println("Libro no encontrado");
        }
    }
    private void listaDeLibros(){
        System.out.println("*** Libros buscados ***");
        var listaLibros = repository.findAll();
        if(!listaLibros.isEmpty()){
            List<LibroDTO> libros = listaLibros.stream()
                    .map(l -> new LibroDTO(l.getId(),
                            l.getTitulo(),
                            l.getAutores().stream()
                                    .map(a->a.getNombre())
                                    .collect(Collectors.joining(", ")),
                            l.getIdioma().stream()
                                    .map(i -> i.getLenguaje())
                                    .collect(Collectors.joining(", ")),
                            l.getNumeroDeDescargas()))
                    .toList();

            System.out.println("*** Libros ***");
            for (var libro : libros){
                System.out.printf("""
                Título: %s
                Autor: %s
                Idiomas: %s
                Descargas: %s
                
                """,libro.titulo(),libro.autores(),libro.idiomas(),libro.descargas());
            }
        }
    }
}
