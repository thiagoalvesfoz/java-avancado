package com.learning.funcional.lambda;

/**
 * Como utilizar interfaces funcionais
 */

public class CriandoLambdas {

    public static void main(String[] args) {

        MinhaFuncao func = id -> {
            if (id % 2 != 0) {
                return "User Not Found";
            }
            return "Thiago Alves";
        };

        System.out.println("\nUtilizando as novas features do Java 8+:");
        System.out.println(func.buscarUsuario(1));
        System.out.println(func.buscarUsuario(12));


        // Antes do Java 8
        MinhaFuncao jeitoAntigo = new MinhaFuncao() {
            @Override
            public String buscarUsuario(Integer id) {
                if (id % 2 != 0) {
                    return "User Not Found";
                }
                return "Thiago Alves";
            }
        };

        System.out.println("\nImplementação Funcional antiga:");
        System.out.println(jeitoAntigo.buscarUsuario(1));
        System.out.println(jeitoAntigo.buscarUsuario(12));

    }
}

@FunctionalInterface
interface MinhaFuncao {
    String buscarUsuario(Integer id);
}
