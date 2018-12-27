package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Calculation extends Thread {

    private final int ai = 0;
    private final int bi = 1;

    private int[][] pairs;
    private int[][] array_c;
    private int[] phase;
    private int id, segmLength;
    private CyclicBarrier barrier;

    public Calculation(int[][] pairs, int[][] array_c, int[] phase, int id, int segmLength, CyclicBarrier barrier) {

        this.pairs = pairs;
        this.array_c = array_c;
        this.phase = phase;
        this.id = id;
        this.segmLength = segmLength;
        this.barrier = barrier;
    }

    @Override
    public void run() {

        initArrayC();
        waitPhase();

        collectPhase();
        waitPhase();
    }

    private void initArrayC() {

        int c_a = pairs[ai][id * segmLength];
        int c_b = pairs[bi][id * segmLength];
        int elem;

        for (int i = 1; i < segmLength; i++) {
            elem = id * segmLength + i;
            c_a = c_a * pairs[ai][elem];
            c_b = pairs[ai][elem] * c_b + pairs[bi][elem];
        }

        array_c[ai][id] = c_a;
        array_c[bi][id] = c_b;
    }

    private void collectPhase() {

        int height = 1;
        while (id - height >= 0) {
            if ((id + 1) % (height * 2) != 0) {
                return;
            }

            while (phase[id] != phase[id - height]) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            array_c[bi][id] = array_c[ai][id] * array_c[bi][id - height] + array_c[bi][id];
            array_c[ai][id] = array_c[ai][id] * array_c[ai][id - height];

            phase[id]++;
            height *= 2;

        }

    }

    private void waitPhase() {

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
