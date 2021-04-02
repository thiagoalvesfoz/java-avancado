package com.learning.processamento;

public class ProcessoAssincrono {

    public static void main(String[] args) {

        GeradorPDF geradorPDF = new GeradorPDF();
        BarraDeCarregamento barraDeCarregamento = new BarraDeCarregamento(geradorPDF);

        geradorPDF.start();
        barraDeCarregamento.start();

    }

}

class GeradorPDF extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Gerando PDF");
            Thread.sleep(5000);
            System.out.println("\r" + "PDF Gerado");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BarraDeCarregamento extends Thread {

    private Thread thread;

    public BarraDeCarregamento(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        String loading = "\r" + "Loading";

        while (true) {
            try {

                if (!thread.isAlive()) {
                    break;
                }

                System.out.print(loading);

                Thread.sleep(500);

                if (loading.contains("...")) {
                    loading = "\r" + "Loading";
                } else {
                    loading += ".";
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
