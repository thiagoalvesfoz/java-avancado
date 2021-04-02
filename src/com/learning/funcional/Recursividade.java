package com.learning.funcional;

import java.util.HashMap;
import java.util.Map;

/**
 * Tail Call (Recursividade em cauda): Recursão em cauda é uma recursão onde não há nenhuma linha de código
 * após a chamada do próprio método e, sendo assim, não há nenhum tipo de processamento a ser feito após a chamada
 * recursiva.
 *
 * OBS: A JVM não suporta a recursão em cauda, ela lança um estouro de pilha (StackOverFlow)
 */
// INDICADO PARA PROCESSAR POUCOS DADOS, POIS UTILIZA DA MEMORIA DO COMPUADOR PARA ARMAZENAR VALORES
public class Recursividade {

    public static void main(String[] args) {

        long inicio, fim;
        int FATORIAL = 16;

        System.out.println("### Recursividade Padrão - Empilhamento ###");
        inicio = System.nanoTime();
        System.out.println(FATORIAL + "! = " + Tecnica.fatorialEmpilhamento(FATORIAL));
        fim = System.nanoTime();
        System.out.println("Tempo em nano-segundos: " + cacularTempo(inicio, fim));

        // TÉCNICA CALDA LONGA
        System.out.println("\n### Recursividade - Tail Call ###");
        inicio = System.nanoTime();
        System.out.println(FATORIAL + "! = " + Tecnica.fatorialTailCall(FATORIAL));
        fim = System.nanoTime();
        System.out.println("Tempo em nano-segundos: " + cacularTempo(inicio, fim));

        // TÉCNICA MEMOIZATION
        System.out.println("\n### Recursividade - Memoization ###");
        inicio = System.nanoTime();
        System.out.println(FATORIAL + "! = " + Tecnica.fatorialMemoization(FATORIAL));
        fim = System.nanoTime();
        System.out.println("Tempo em nano-segundos: " + cacularTempo(inicio, fim) + "\n");

        inicio = System.nanoTime();
        System.out.println(FATORIAL + "! = " + Tecnica.fatorialMemoization(FATORIAL));
        fim = System.nanoTime();
        System.out.println("Tempo em nano-segundos: " + cacularTempo(inicio, fim));
    }

    public static Long cacularTempo(Long inicio, Long fim) {
//        Long nanoseconds =  fim - inicio;
//        return  (double)nanoseconds / 1_000_000_000.0;
        return fim - inicio;
    }
}

/**
 * RECURSIVIDADE: técnica onde uma função invoca a si mesma empilhando subrotinas até que atinja a condição
 * de parada resolvendo as subrotinas empilhadas.
 *
 * TAIL-CALL OPTIMIZATION: Tecnica (Calda Longa) que otimiza o empilhamento da chamada recursiva
 * Utiliza um parâmetro a mais na função, que funciona como acumulador, a cada chamada recursiva
 * o acumulador é atualizado com o resultado parcial, no final, a função retorna o valor do acumulador.
 *
 * MEMOIZATION: Técnica de otimização que consiste no cache do resultado
 * de uma função(MAPA), baseado nos parâmetros de entrada. Com isso, nas seguintes
 * execuções conseguimos ter uma resposta mais rápida.
 */

class Tecnica {
    static Map<Integer, Integer> MAPA_FATORIAL = new HashMap<>();

    public static Integer fatorialMemoization(Integer value) {

        if (value == 1) {
            return value;
        }

        else if (MAPA_FATORIAL.containsValue(value)) {
            return MAPA_FATORIAL.get(value);
        }

        else {
            Integer result = value * fatorialMemoization(value - 1);
            MAPA_FATORIAL.put(value, result);
            return result;
        }

    }
    // Isolamento Tail Call
    public static int fatorialTailCall(int fator) {
        // fatorialComTailCall(5, 1)        -> Valores Iniciais
        // fatorialComTailCall(4, 1 * 5)    -> result 5
        // fatorialComTailCall(3, 5 * 4)    -> result 20
        // fatorialComTailCall(2, 20 * 3)   -> result 60
        // fatorialComTailCall(1, 60 * 2)   -> result 120
        // fatorialComTailCall(0, 120 * 1)  -> result 120
        return Tecnica.fatorialComTailCall(fator, 1);
    }

    // Recursividade com Tail Call
    private static int fatorialComTailCall(int iteration, int accumulator) {

        if (iteration == 0) {
            return accumulator;
        }
        // chama a própria função já resolvendo o valor até chegar
        return fatorialComTailCall(iteration - 1, accumulator * iteration);
    }

    // Recursividade Padrão (Empilhamento de chamadas)
    public static int fatorialEmpilhamento(int value) {

        if (value == 1) {
            return value;
        }

        // chama a própria função empilhando até chegar no número 1 e então voltar resolvendo as expressões
        return value * (fatorialEmpilhamento(value - 1));
    }
}
