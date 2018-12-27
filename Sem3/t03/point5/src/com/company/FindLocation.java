package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FindLocation extends Thread {

    private final int X = 0;
    private final int Y = 1;

    private double[] angle, length;
    private double[] array_angl;
    private double[][] array_coor;
    private int[] phase;
    private boolean[] wait;
    private int id, segmLength, countThread;
    private CyclicBarrier barrier;

    public FindLocation(double[] angle, double[] length, double[] array_angl, double[][] array_coor,
                        int[] phase, boolean[] wait, int id, int segmLength, int countThread, CyclicBarrier barrier) {

        this.angle = angle;
        this.length = length;
        this.array_angl = array_angl;
        this.array_coor = array_coor;
        this.phase = phase;
        this.wait = wait;
        this.id = id;
        this.segmLength = segmLength;
        this.countThread = countThread;
        this.barrier = barrier;

    }

    @Override
    public void run() {

        initArrayAngle();
        waitPhase();
        collectPhaseAngle();
        waitPhase();
        distributePhaseAngle();
        waitPhase();

        update();
        waitPhase();

        initArrayCoor();
        waitPhase();
        collectPhaseCoor();
        waitPhase();
    }

    private void initArrayAngle() {

        int elem;
        double alpha;

        for (int i = 0; i < segmLength; i++) {
            elem = id * segmLength + i;
            array_angl[id] = array_angl[id] + angle[elem];
            angle[elem] = array_angl[id];
        }
    }

    private void initArrayCoor() {

        int elem;
        double alpha;
        for (int i = 0; i < segmLength; i++) {
            elem = id * segmLength + i;
            alpha = Math.toRadians(angle[elem]);
            array_coor[X][id] = array_coor[X][id] + Math.round(length[elem] * Math.cos(alpha));
            array_coor[Y][id] = array_coor[Y][id] + Math.round(length[elem] * Math.sin(alpha));
        }
    }

    private void collectPhaseAngle() {

        int height = 1;
        while (id - height >= 0) {
            if ((id + 1) % (height * 2) != 0) {
                return;
            }

            while (phase[id] != phase[id - height]) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            array_angl[id] = (array_angl[id] + array_angl[id - height]) % 360;

            phase[id]++;
            height = height * 2;
        }
    }

    private void collectPhaseCoor() {

        int height = 1;
        while (id - height >=  0) {
            if ((id + 1) % (height * 2) != 0) {
                return;
            }

            while (phase[id] != phase[id - height]) {
                try {
                    Thread.sleep(11);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            array_coor[X][id] = array_coor[X][id] + array_coor[X][id - height];
            array_coor[Y][id] = array_coor[Y][id] + array_coor[Y][id - height];

            phase[id]++;
            height = height * 2;
        }
    }

    private void distributePhaseAngle() {

        int height = 1;
        if (id % 2 == 0) {
            wait[id] = true;
        }

        if (id == countThread - 1) {
            array_angl[id] = 0;
            phase[id]--;
            height = (int)Math.pow(2, phase[id]);
            wait[id] = true;
        } else {
            height = (int)Math.pow(2, phase[id] - 1);
            wait[id] = true;
        }

        while (phase[id] >= 0) {
            if (phase[id - height] == phase[id] && wait[id - height]) {
                double temp = array_angl[id - height];
                array_angl[id - height] = array_angl[id];
                array_angl[id] = array_angl[id] + temp;
                phase[id]--;
                phase[id - height]--;

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

    private void update() {

        int elem;
        for (int i = 0; i < segmLength; i++) {
            elem = id * segmLength + i;
            angle[elem] = (angle[elem] + array_angl[id]) % 360;
        }

        phase[id] = 0;
    }

    private void waitPhase() {

        try{
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
