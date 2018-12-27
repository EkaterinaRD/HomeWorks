package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Crawler implements Runnable {

    private String link;
    private WebCrawler current;
    private int depth, id;
    private Lock lock;

    public Crawler(String link, WebCrawler current, int depth, int id) {

        this.link = link;
        this.current = current;
        this.depth = depth;
        this.id = id;
        lock = new ReentrantLock();
    }

    @Override
    public void run() {

        Document document;
        try {
            document = Jsoup.connect(link).get();
            //System.out.println("get page #" + id + " " + link);
            if (depth > 0) {
                Elements link_page = document.select("a[href]");
                for (Element page : link_page) {
                    String link = page.attr("abs:href");
                    try {
                        lock.lock();
                        if (!current.isVisited(link)) {
                            current.addThread(link, depth - 1);
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }

            downPage(document);
            current.endThread();
        } catch (Exception e) {
            current.endThread();
            //System.out.println("Error " + id + " " + link);
        }
    }

    private void downPage(Document document) throws Exception {

        File file = new File("site/page #" + id + ".html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(document.outerHtml());
    }
}
