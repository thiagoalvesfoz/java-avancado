package com.learning.processamento;


/**
 *
 */

public class ThreadExample {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Process1());
        Thread thread2 = new Thread(new Process2());

        thread1.start();
        thread2.start();
        System.out.println("Nome da thread1 -> " + thread1.getName());
        System.out.println("Nome da thread2 -> " + thread2.getName());
    }
}

class Process1 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Processo 1 iniciado");
            Thread.sleep(5000);
            System.out.println("Processo 1 finalizado");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Process2 implements Runnable  {
    @Override
    public void run() {
        try {
            System.out.println("Processo 2 iniciado");
            Thread.sleep(3000);
            System.out.println("Processo 2 finalizado");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
