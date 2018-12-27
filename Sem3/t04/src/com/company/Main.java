package com.company;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final int THREADS = 4;

    public static void main(String[] args) {

        String url = "http://alexei.stepanov.spb.ru/";
        int depth = 1;
        AtomicInteger count = new AtomicInteger(0);
        //ExecutorService threadPool = Executors.newFixedThreadPool(THREADS);
        MyExecuteService threadPool = new MyExecuteService(THREADS);

        WebCrawler crawler = new WebCrawler(count, threadPool);
        crawler.addThread(url, depth);
        while (count.get() != 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();

    }
}
