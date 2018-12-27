package com.company;

//import java.util.Set;
//import java.util.concurrent.ConcurrentSkipListSet;
//import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class WebCrawler {

    private AtomicInteger count;
    private AtomicInteger ID;
    //private Set<String> visit;
    private MySet<String> visit;
    //private ExecutorService threadPool;
    private MyExecuteService threadPool;

    public WebCrawler(AtomicInteger count, MyExecuteService threadPool) {

        //visit = new ConcurrentSkipListSet<>();
        visit = new MySet<>();
        this.count = count;
        this.threadPool = threadPool;
        ID = new AtomicInteger(0);
    }

    public void addThread(String link, int depth) {

        count.getAndIncrement();
        Runnable task = new Crawler(link, this, depth, ID.getAndIncrement());
        visit.add(link);
        threadPool.execute(task);
    }

    public boolean isVisited(String link) {

        return visit.contains(link);
    }

    public void endThread() {

        count.getAndDecrement();
    }
}
