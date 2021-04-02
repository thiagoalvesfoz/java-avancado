package com.learning.funcional.interfaces;

import java.util.function.Supplier;

/**
 * SUPPLIER: É uma interface funcional que não recebe parametros mas retorna um tipo genérico
 */
public class SupplierInterface {
    public static void main(String[] args) {
        Supplier<Pessoa> instanciaPessoa = Pessoa::new;
        System.out.println(instanciaPessoa.get());
    }
}

class Pessoa {
    String nome;
    int idade;

    public Pessoa() {
        this.nome = "Thiago";
        this.idade = 23;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
