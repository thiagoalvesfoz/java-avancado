package com.learning.funcional.interfaces;

import java.util.function.UnaryOperator;

public class UnaryOperatorInterface {

    public static void main(String[] args) {

        final int NUMBER = 10; //instrução

        /* No paradigma imperativo, expressamos o código atráves de instruções
         * onde temos o total controle sobre cada instrução
         */

        int resultado = NUMBER * 3; //instrução
        System.out.println("O resultado é " + resultado);

        /* No paradigma funcional, damos uma regra, uma declaração de como
         * queremos que o programa se comporte
         */
        UnaryOperator<Integer> calculaValorVezes3 = value -> value * 3;
        System.out.println("O resultado é " + calculaValorVezes3.apply(NUMBER));

        UnaryOperator<Integer> operator1 = t -> t + 5; // 5 = 5 + 5 = 10
        UnaryOperator<Integer> operator2 = t -> t * 5; // 5 = 5 * 5 = 25

        // Using andThen() method
        // chama primerio o operator1 (principal), e depois operator2
        int a = operator1.andThen(operator2).apply(5); //
        System.out.println(a);

        // Using compose() method
        // chama primeiro o operator2, o resultado final do argumento2 será o valor que irá compor o operator1
        int b = operator1.compose(operator2).apply(5); // 2> 5 * 5 = 25; 1> 25 + 5 = 30;
        System.out.println(b);

    }



}
