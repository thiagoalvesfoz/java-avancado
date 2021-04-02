package com.learning.funcional.interfaces;

import java.util.function.Function;

/**
 * A Funtion Interface é um uma interface lambda que aceita um parâmetro de tipo genérico
 * e um retorno também do tipo genérico
 */

public class FunctionInterface {

    public static void main(String[] args) {


        Function<String, String> nomeAoContrario = text -> new StringBuffer(text).reverse().toString();
        System.out.println(nomeAoContrario.apply("clara"));

        Function<String, String> toFormat = "Sr. "::concat;
        System.out.println(toFormat.apply("Thiago"));

    }

}
