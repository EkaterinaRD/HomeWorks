package com.company;

import java.util.concurrent.CyclicBarrier;

public class StartProcess {

    private final int ai = 0;
    private final int bi = 1;

    private int[][] pairs;
    private int[][] array_c;
    private int countThread, n, result;
    private Calculation[] thread;
    private CyclicBarrier barrier;
    private int segmLength;
    private int[] phase;

    public StartProcess (int[][] pairs, int n, int countThread) {
        this.pairs = pairs;
        this.n = n;
        this.countThread = countThread;
        init();
    }

    private void init() {

        result = 0;
        array_c = new int[2][countThread];
        thread  = new Calculation[countThread];
        barrier = new CyclicBarrier(countThread);
        segmLength = n / countThread;
        phase = new int[countThread];
    }

    public int Calculate() {

        for (int id = 0; id < countThread; id++) {
            thread[id] = new Calculation(pairs, array_c, phase, id, segmLength, barrier);
            thread[id].start();
        }

        for (int id = 0; id < countThread; id++) {
            try {
                thread[id].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return array_c[bi][countThread - 1];
    }

    public int simpleCalculate() {

        result = pairs[bi][0];
        for (int i = 1; i <= n - 1; i++) {
            result = pairs[ai][i] * result + pairs[bi][i];
        }
        return result;
    }
}
