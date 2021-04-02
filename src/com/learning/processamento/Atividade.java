package com.learning.processamento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Exemplo de multi-threads com ExecutorService e Executors.newFixedThreadPool()
 */

class App {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(3); //limita a 3 threads

    public static void main(String[] args) {
        Casa casa = new Casa(new Quarto());
        realizarTarefas(casa);
    }

    // Cada atividade será realizada de forma assíncrona.
    public static void realizarTarefas(Casa casa) {
        casa.obterAfazeresDaCasa().forEach( atividade -> threadPool.execute(atividade::realizar) ); // ou -> threadPool.execute(() -> atividade.realizar())
        threadPool.shutdown();
    }

}

class Casa {

    private List<Comodo> comodos;

    Casa(Comodo... comodos) {
        this.comodos = Arrays.asList(comodos);
    }

    List<Atividade> obterAfazeresDaCasa() {
        return this.comodos.stream().
                map(Comodo::obterAfazeresDoComodo). // ou -> map( comodo -> comodo.obterAfazeresDaCasa())
                reduce(new ArrayList<>(), (pivot, atividade) -> {
                    pivot.addAll(atividade);
                    return pivot;
                });
    }

}


abstract class Comodo {
    abstract List<Atividade> obterAfazeresDoComodo();
}

interface Atividade {
    void realizar();
}

class Quarto extends Comodo {

    @Override
    List<Atividade> obterAfazeresDoComodo() {
        return Arrays.asList(
                this::arrumarGuardaRoupa, // ou -> () -> this.arrumarGuardaRoupa()
                this::arrumarACama,
                this::varrerOQuarto
        );
    }

    // Cria o corpo da interface funcional Atividade
    private void arrumarGuardaRoupa() {
        System.out.println("Arrumando o guarda roupa.");
    }

    // Cria o corpo da interface funcional Atividade
    private void varrerOQuarto() {
        System.out.println("Varrendo o quarto.");
    }

    // Cria o corpo da interface funcional Atividade
    private void arrumarACama() {
        System.out.println("Arrumando a cama.");
    }
}
