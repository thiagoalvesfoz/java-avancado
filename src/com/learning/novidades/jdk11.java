package com.learning.novidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * HTTP CLIENT API
 * Um dos recursos que foram incluídos na próxima versão do JDK 11 é a API do cliente HTTP padronizada
 * que visa substituir a class HttpUrlConnection legada, que está presente no JDK desde os primeiros anos do
 * Java.  O problema com essa API antiga é descrito na proposta de aprimoramento, principalmente porque agora
 * ela é  considerada antiga  e difícil de usar.
 *
 * A nova API suporta HTTP / 1.1 e HTTP / 2. A versão mais recente do protocolo HTTP foi projetada para melhorar
 * o desempenho geral do envio de solicitações por um cliente e do recebimento de respostas do servidor. Isso é
 * conseguido através da introdução de várias alterações, como multiplexação de fluxo, compactação de cabeçalho e
 * Push Promise. Além disso, o novo cliente HTTP também suporta nativamente WebSockets.
 */

public class jdk11 {

    private final static String URL_ORACLE = "https://docs.oracle.com/javase/10/language/";
    private final static String URL_AKAMAI = "https://http2.akamai.com/demo/h2_demo_frame.html";

    private final static ExecutorService threadPool = Executors.newFixedThreadPool(6, runnable -> {
            Thread thread = new Thread(runnable);
            System.out.println("Nova thread criada : " + (thread.isDaemon() ? "daemon ": "") + "Thread Group :" + thread.getThreadGroup());
            return thread;
        }
    );

    public static void main(String[] args) throws Exception {
        //connectAndPrintURLJavaOracle();
        connectAkamaiHttpClient(HttpClient.Version.HTTP_1_1);
//        connectAkamaiHttpClient(HttpClient.Version.HTTP_2);
    }

    public static void connectAkamaiHttpClient(HttpClient.Version httpVersion) throws IOException, InterruptedException {

        String version = httpVersion.name().equals("HTTP_2") ? "2" : "1.1";
        System.out.println("Running HTTP/" + version + " example...");

        long start = System.currentTimeMillis();

        try {
            HttpClient httpClient = HttpClient.newBuilder().
                    version(httpVersion).
                    proxy(ProxySelector.getDefault()).
                    build();



            HttpRequest request = HttpRequest.newBuilder().
                    uri(URI.create(URL_AKAMAI)).
                    build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Headers: \n" + response.headers());
            System.out.println("\nbody: \n" + response.body());

            List<Future<?>> futures = new ArrayList<>();

            response.body().lines().filter(line -> line.trim().startsWith("<img height"))
                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = threadPool.submit(() -> {
                            HttpRequest imgRequest = HttpRequest.newBuilder().
                                    uri(URI.create("https://http2.akamai.com" + image)).
                                    build();

                            try {
                                HttpResponse<String> imgResponse = httpClient.send(imgRequest, HttpResponse.BodyHandlers.ofString());
                                System.out.println("Imagem carregada : " + image + ", Status code: " + imgResponse.statusCode());
                            } catch (IOException | InterruptedException e) {
                                System.out.println("Ocorreu um erro durante a requisião da imagem");
                            }
                        });
                        futures.add(imgFuture);
                        System.out.println("Submetido um futuro para imagem:  " + image);
                    });

            futures.forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Ocorreu um erro ao carregar as imagens de future");
                }
            });

        } finally {
            threadPool.shutdown();
        }

        long end = System.currentTimeMillis();
        System.out.println("Tempo de carregamento total: " + (end - start) + "ms");
    }

    public static void connectAndPrintURLJavaOracle() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                GET().uri(URI.create(URL_ORACLE)).
                build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Headers: \n" + response.headers());
        System.out.println("\nbody: \n" + response.body());
    }

    public static void connectAndPrintURLJavaOracle_MetodoAntigo() throws IOException {
        var url = new URL(URL_ORACLE);
        var urlConnection = url.openConnection();

        try(var bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {

            var result = bufferedReader.lines().collect(Collectors.joining()).replaceAll(">", ">\n");
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
