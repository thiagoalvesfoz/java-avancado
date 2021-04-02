package com.learning.novidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;


/**
 * JDK 10 TROUXE A INFERÊNCIA DE TIPOS
 *
 * NÃO PERMITE INFERÊNCIA DE TIPOS EM:
 * - Atributos de classes
 * - Parâmetros de métodos
 * - valores não inicializados
 *
 * PERMITE INFERÊNCIA DE TIPOS EM:
 * - Variáveis locais
 * - for
 */

public class Jdk10 {
    public static void main(String[] args) throws IOException {
        requisicao();
        requisicaoComInferenciaDeTipo();
    }

    public static void requisicao() throws IOException {
        URL url = new URL("https://docs.oracle.com/javase/10/language/");
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String result = bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n");
        System.out.println(result);
    }

    public static void requisicaoComInferenciaDeTipo() throws IOException {
        var url = new URL("https://docs.oracle.com/javase/10/language/");
        var urlConnection = url.openConnection();
        var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        var result = bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n");
        System.out.println(result);
    }
}