package com.learning.processamento;

import java.util.stream.IntStream;

/**
 * Quando Utilizar o Pararel Stream?
 * Utiliza-se quando é necessário tratar grandes quantidade de processamentos (ex: lista com 10 mil objetos),
 * e que cada processo não dependa de outro.
 *
 */
public class ParallelStreamExample {
    public static void main(String[] args) {

        executeSerialStream();

        // Indicado para ganhar performance em alto processamento de dados.
        executeParallelStream();

    }

    public static void executeSerialStream() {
        long inicio, fim;
        inicio =  System.currentTimeMillis();
        IntStream.range(1, 100000).forEach(num ->fatorial(num));
        fim =  System.currentTimeMillis();
        System.out.println("Tempo de execução serial: " + cacularTempo(inicio, fim));
    }

    public static void executeParallelStream() {
        long inicio, fim;
        inicio =  System.currentTimeMillis();
        IntStream.range(1, 100000).parallel().forEach(num ->fatorial(num));
        fim =  System.currentTimeMillis();
        System.out.println("Tempo de execução parallel: " + cacularTempo(inicio, fim));
    }

    public static long fatorial(int fatorial) {

        long accumulator = 1;

        for (int i = fatorial; i > 1; i--) {
            accumulator *= i;
        }

        return accumulator;
    }

    public static Long cacularTempo(Long inicio, Long fim) {
        return fim - inicio;
    }
}
