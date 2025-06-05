package com.hernandezedwin.LiterBook.principal;

import com.hernandezedwin.LiterBook.model.DatosAutor;
import com.hernandezedwin.LiterBook.model.DatosLibro;
import com.hernandezedwin.LiterBook.model.DatosLibros;
import com.hernandezedwin.LiterBook.service.ConsumoApiService;
import com.hernandezedwin.LiterBook.service.ConversorDatos;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/";
    private ConsumoApiService api= new ConsumoApiService();
    private ConversorDatos conversor = new ConversorDatos();
    public void menu()
    {
        int opcion = 1;
        while(opcion != 0){
            System.out.println("""
                \t\tMenu
                1- Buscar libro por titulo
                0- Salir
                """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 0:
                    System.out.println("Cerrando ...");
                    break;
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
    }
}
