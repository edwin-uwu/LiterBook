package com.hernandezedwin.LiterBook.principal;

import com.hernandezedwin.LiterBook.dto.AutorDTO;
import com.hernandezedwin.LiterBook.dto.LibroDTO;
import com.hernandezedwin.LiterBook.model.*;
import com.hernandezedwin.LiterBook.repository.LibroRepository;
import com.hernandezedwin.LiterBook.service.ConsumoApiService;
import com.hernandezedwin.LiterBook.service.ConversorDatos;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                3- Listar autores
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
                case 3:
                    listarAutores();
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
        System.out.println(json);
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
    private void listarAutores(){
        //List<AutorDTO> listaAutores = new ArrayList<>();
        List<Libro> autores = repository.findAll();
        Set<AutorDTO>  listaAutores = new HashSet<>();


        System.out.println("Autores" + autores.size());
        autores.forEach(l ->{
            l.getAutores().forEach(a->{
                AutorDTO autorBusqueda = new AutorDTO(
                        0L,
                        a.getNombre(),
                        a.getFechaNacimiento(),
                        a.getFechaMuerte()
                );
                listaAutores.add(autorBusqueda);
            });
        });

        /*for(Libro libroActual: autores)
        {
            System.out.println("Libro: " + libroActual.getTitulo());
            for(var listaAuto: libroActual.getAutores()){
                AutorDTO autorBusqueda = new AutorDTO(
                        0L,
                        listaAuto.getNombre(),
                        listaAuto.getFechaNacimiento(),
                        listaAuto.getFechaMuerte()
                );
                if(!listaAutores.contains(autorBusqueda))
                {
                    listaAutores.add(autorBusqueda);
                }else{
                    System.out.println("Ya existe");
                }
            }
        }*/

        System.out.println("Lista de autores");
        listaAutores.forEach(a->{
            System.out.printf("""
                    Autor: %s
                    Año de Nacimiento: %s
                    Año de muerte: %s
                    \n
                    """,a.nombre(),
                    a.fechaNacimiento() == null ? "N/A" :a.fechaNacimiento(),
                    a.fechaMuerte() == null ?"N/A":a.fechaMuerte());
        });
        /*for (int i=0; i < autores.size();i++)
        {
            List<AutorDTO> libroActual = autores.get(0).getAutores().stream()
                    .map(a-> new AutorDTO(a.getId(),a.getNombre(),a.getFechaNacimiento(),a.getFechaMuerte()))
                    .toList();
            //if(listaAutores.contains(""))
            /*AutorDTO autor = autores.get(i).getAutores().stream()
                    .map(a -> new AutorDTO(a.getId(),a.getNombre(),a.getFechaNacimiento(),a.getFechaMuerte()))
                    .collect(Collectors.to);
            System.out.println(libroActual);
        }*/

    }
}
