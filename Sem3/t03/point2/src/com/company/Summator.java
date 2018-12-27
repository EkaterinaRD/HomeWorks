package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Summator extends Thread {

    private final int C = 2;
    private final int M = 1;
    private final int N = 0;

    private int[] int_a, int_b, int_c;
    private int[] sum, carries, value;
    boolean[] wait;
    private int countThread, segmLen, id;
    private CyclicBarrier barrier;

    public Summator(int[] int_a, int[] int_b, int[] int_c, int[] sum, int[] carries, int[] value, boolean[] wait, int countThread, int segmLen, int id, CyclicBarrier barrier) {

        this.int_a = int_a;
        this.int_b = int_b;
        this.int_c = int_c;
        this.sum = sum;
        this.carries = carries;
        this.value = value;
        this.wait = wait;
        this.countThread = countThread;
        this.segmLen = segmLen;
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {

        prepare();
        waitPhase();
        collectPhase();
        waitPhase();
        distributePhase();
        waitPhase();
        finalCarr();
        waitPhase();
        result();
        waitPhase();
    }

    private void prepare() {

        int elem, carr;
        int summ = M;

        for (int i = 0; i < segmLen; i++) {
            elem = id * segmLen + i;
            sum[elem] = (int_a[elem] + int_b[elem]) % 10;
            carr = getCarr(int_a[elem] + int_b[elem]);
            summ = getSumCarr(summ, carr);
            carries[i] = summ;
        }
        int_c[id] = summ;
    }

    private int getCarr(int sum) {

        if (sum == 9) {
            return M;
        } else if (sum > 9) {
            return C;
        } else {
            return N;
        }
    }

    private int getSumCarr(int fst, int snd) {

        if (fst == snd) {
            return fst;
        }

        if (fst == M || snd == M) {
            return fst == M ? snd : fst;
        }

        return snd;
    }

    private void collectPhase() {

        int height = 1;
        while (id - height >= 0) {
            if ((id + 1) % (height * 2) != 0) {
                return;
            }

            while (value[id] != value[id - height]) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int_c[id] = getSumCarr(int_c[id - height], int_c[id]);
            value[id]++;
            height = height * 2;
        }
    }

    private void distributePhase() {

        int height;
        if (id % 2 == 0) {
            wait[id] = true;
            return;
        }

        if (id == countThread - 1) {
            int_c[id] = M;
            value[id]--;
            height = (int)Math.pow(2, value[id]);
            wait[id] = true;
        } else {
            height = (int)Math.pow(2, value[id] - 1);
            wait[id] = true;
        }

        while (value[id] >= 0) {
            if (value[id - height] == value[id] && wait[id - height]) {
                int temp = int_c[id - height];
                int_c[id - height] = int_c[id];
                int_c[id] = getSumCarr(temp, int_c[id]);

                value[id]--;
                value[id - height]--;
                height = height / 2;
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void finalCarr() {

        int elem;
        int num = int_c[id];
        for (int i = 0; i < segmLen; i++) {
            elem = id * segmLen + i;
            carries[elem] = getSumCarr(num, carries[elem]);
        }
    }

    private void result() {
        int elem;
        for (int i = 0; i < segmLen; i++) {
            elem = id * segmLen + i;
            if (elem >= 0 && carries[elem] == C) {
                sum[elem + 1] = (sum[elem + 1] + 1) % 10;
            }
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
