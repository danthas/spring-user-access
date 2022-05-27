package org.balicki.AccesoUsuario.model;

import java.util.SortedMap;
import java.util.TreeMap;

public class Colecciones {
    public static SortedMap<String, String> listaGenerosOrdenada = new TreeMap<>();
    public static SortedMap<String, String> listaPaisesOrdenada = new TreeMap<>();
    public static SortedMap<String, String> listaAficionesOrdenada = new TreeMap<>();
    public static SortedMap<String, String> listaMusicasOrdenada = new TreeMap<>();

    static {
        // Lista de generos
        listaGenerosOrdenada.put("H", "Hombre");
        listaGenerosOrdenada.put("M", "Mujer");
        listaGenerosOrdenada.put("O", "Otro");
        listaGenerosOrdenada.put("F", "Fluido");
        // Lista de paises
        listaPaisesOrdenada.put("AN", "Andorra");
        listaPaisesOrdenada.put("ES", "España");
        listaPaisesOrdenada.put("PT", "Portugal");
        listaPaisesOrdenada.put("FR", "Francia");
        listaPaisesOrdenada.put("IT", "Italia");
        // Lista de aficiones
        listaAficionesOrdenada.put("C", "Cine");
        listaAficionesOrdenada.put("D", "Deporte");
        listaAficionesOrdenada.put("L", "Lectura");
        listaAficionesOrdenada.put("M", "Musica");
        // Lista de musica
        listaMusicasOrdenada.put("P", "Pop");
        listaMusicasOrdenada.put("R", "Rock");
        listaMusicasOrdenada.put("E", "Electronica");
        listaMusicasOrdenada.put("N", "New Age");
        listaMusicasOrdenada.put("F", "Funk");
    }
}

