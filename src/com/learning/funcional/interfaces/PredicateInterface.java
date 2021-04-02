package com.learning.funcional.interfaces;

import java.util.function.Predicate;
/*
 * Predicates são funções que recebe um valor como parâmetro para validação, retornando true ou falso
 */
public class PredicateInterface {
    public static void main(String[] args) {

        Predicate<String> estaVazio = String::isEmpty;
        System.out.println(estaVazio.test(""));
        System.out.println(estaVazio.test("Thiago"));
    }
}
