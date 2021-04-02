package com.learning.funcional;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * FUNÇÕES PURAS: É chamada de retorne sempre o mesmo resultado apos ser invocado mais de uma vez.
 */

public class Imutabilidade {
    public static void main(String[] args) {

        int[] valores = {1, 2, 3, 4};

        // Resolvendo no paradigma funcional
        Arrays.stream(valores).
                filter(number -> number % 2 == 0).  // filtra todos os valores que são pares
                map(number -> number * 2).          // multiplica os valores filtrados por 2
                forEach(System.out::println);       // faz o print do resultado

        // Resolvendo no paradigma imperativo
        System.out.println("#######################");
        for (int valor : valores) {

            if (valor % 2 == 0)  {
                valor *= 2;
                System.out.println(valor);
            };

        }


        // FUNÇÕES PURAS - Imutabilidade
        BiPredicate<Integer, Integer> verificaSeEhMaior = (param1, param2) -> param1 > param2;
        System.out.println(verificaSeEhMaior.test(13, 12));
        System.out.println(verificaSeEhMaior.test(13, 14));

        int valor = 10;

        UnaryOperator<Integer> duplica = v -> {
            v = 50; // alterando propositamente o valor recebido
            return v * 2;
        };

        System.out.println(valor);
        System.out.println(duplica.apply(valor));


        Function<Integer, Object> findUserById = id -> new Object();
        Object user = findUserById.apply(1);
    }
}
