package com.learning.funcional.interfaces;

import java.util.function.Consumer;

/**
 * CONSUMER: é uma interface funcional que recebe um parâmetro mas não possui retorno
 */
public class ConsumerInterface {
    public static void main(String[] args) {

        Consumer<String> imprimirUmaFrase = System.out::println;
        Consumer<String> imprimirUmaFrase2 = text -> System.out.println(text);
        imprimirUmaFrase.accept("Hello World");
        imprimirUmaFrase2.accept("Hello World");
    }
}
