package com.learning.funcional;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Iteracoes {

    public static void main(String[] args) {
        String[] nomes = {"João", "Thiago", "Oliveira", "Instrutor", "Java"};
        Integer[] numeros = {1, 2, 3, 4, 5};
        imprimirNomesComFor(nomes);
        imprimirNomesComLambdas(nomes);
        imprimirTodosOsNomes(nomes);
        imprimirODobroDeCadaNumero(numeros);

    }

    public static void imprimirNomesComFor(String... nomes) {

        String nomesParaImprimir = "";

        for (String nome : nomes) {
            if (nome.equals("João")) {
                nomesParaImprimir += nome + " ";
            }
        }

        System.out.println(nomesParaImprimir);
    }

    public static void imprimirNomesComLambdas(String... nomes) {
        String nomesParaImprimir = Stream.of(nomes).
                filter(s -> s.equals("João")).
                collect(Collectors.joining()); // contatena o array transformando em string;

        System.out.println(nomesParaImprimir);
    }

    public static void imprimirTodosOsNomes(String... nomes) {
        System.out.println("\nUtilizando o For tradicional");
        for (String nome : nomes) {
            System.out.print(nome + " - ");
        }

        System.out.println("\nUtilizando o forEach");
        Stream.of(nomes).map( s -> s.concat(" - ")).forEach(System.out::print);
    }

    public static void imprimirODobroDeCadaNumero(Integer... numeros) {
        System.out.println("\n\nUtilizando o For tradicional");
        for (Integer numero : numeros) {
            System.out.print(numero * 2 + " ");
        }

        System.out.println("\nUtilizando o forEach");
        Stream.of(numeros).map( n -> n * 2).forEach(n -> System.out.print(n + " "));
    }

}
