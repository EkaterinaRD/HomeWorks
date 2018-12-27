package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CheckString extends Thread {

    private final int LT = 0;
    private final int RT = 1;

    private char[] string;
    private int[][] array_c;
    private int[] phase;
    private int id, segmLength;
    private CyclicBarrier barrier;

    public CheckString(char[] string, int[][] array_c, int[] phase, int id, int segmLength, CyclicBarrier barrier) {

        this.string = string;
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

        int c_rigth = 0, c_left = 0;
        int elem;
        for (int i = 0; i < segmLength; i++) {
            elem = id * segmLength + i;
            if (string[elem] == ')' && c_left == 0) {
                c_rigth++;
            } else if (string[elem] == ')' && c_left != 0) {
                c_left--;
            } else {
                c_left++;
            }
        }

        array_c[LT][id] = c_left;
        array_c[RT][id] = c_rigth;
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

            brackets(id - height, id);

            phase[id]++;
            height = height * 2;
        }

    }

    private void brackets(int id_1, int id_2) {

        int c_right = 0, c_left = 0;
        if (array_c[LT][id_1] >= array_c[RT][id_2]) {
            c_left  = array_c[LT][id_1] - array_c[RT][id_2] + array_c[LT][id_2];
            c_right = array_c[RT][id_1];
        } else {
            c_right = array_c[RT][id_2] - array_c[LT][id_1] + array_c[RT][id_1];
            c_left  = array_c[LT][id_2];
        }

        array_c[LT][id_2] = c_left;
        array_c[RT][id_2] = c_right;
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
