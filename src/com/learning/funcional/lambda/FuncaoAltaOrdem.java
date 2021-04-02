package com.learning.funcional.lambda;

/**
 * FUNÇÃO DE ALTA ORDEM: é uma função que recebe por parâmetro outra função,
 * ou que retorna uma função
 */
class FuncaoAltaOrdem {
    public static void main(String[] args) {
        //Implementa o comportamento lambda
        Calculo somar       = (a, b) -> a + b;
        Calculo subtrair    = (a, b) -> a - b;
        Calculo dividir     = (a, b) -> a / b;
        Calculo multiplicar = (a, b) -> a * b;

        System.out.println(runOperation(somar, 6, 3));
        System.out.println(runOperation(subtrair, 6, 3));
        System.out.println(runOperation(dividir, 6, 3));
        System.out.println(runOperation(multiplicar, 6, 3));
    }

    // Função de Alta Ordem - recebendo outra função por parâmetro
    public static int runOperation(Calculo calc, int a, int b) {
        return calc.calculo(a, b);
    }
}

@FunctionalInterface
interface Calculo {
    int calculo(int a, int b);
}
