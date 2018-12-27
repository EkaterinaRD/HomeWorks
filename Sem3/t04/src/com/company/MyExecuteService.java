package com.company;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyExecuteService implements Executor {

    private Thread[] thread;
    private MySet<Runnable> queue;
    private Lock lock;
    private boolean shutDown;

    public MyExecuteService(int countThread) {

        thread = new Thread[countThread];
        lock = new ReentrantLock();
        queue = new MySet<>();
        shutDown = false;

        for (int i = 0; i < countThread; i++) {
            thread[i] = new Thread(new PoolTask());
            thread[i].start();
        }
    }

    public void execute(Runnable task) {

        queue.add(task);
    }

    public void shutdown() {

        shutDown = true;
    }

    private final class PoolTask implements Runnable {

        @Override
        public void run() {

            while (!shutDown) {

                Runnable task = null;

                try {
                    lock.lock();
                    if (!queue.isEmpty()) {
                        task = queue.getFirstEl();
                    }
                } finally {
                    lock.unlock();
                }

                if (task != null) {
                    task.run();
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


    }


}
