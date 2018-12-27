package com.company;

import java.util.concurrent.CyclicBarrier;

public class StartProcess {

    private final int X = 0;
    private final int Y = 0;

    private double[] angle, length;
    private double[] array_angl;
    private double[][] array_coor;
    private int[] phase;
    private boolean[] wait;
    private int countThread, lenArr, segmLength;
    private FindLocation[] thread;
    private CyclicBarrier barrier;

    public StartProcess(double[] angle, double[] length, int countThread) {

        this.angle = angle;
        this.length = length;
        this.countThread = countThread;

        init();
    }

    private void init() {

        lenArr = angle.length;
        phase = new int[countThread];
        wait = new boolean[countThread];
        segmLength = lenArr / countThread;
        array_angl = new double[countThread];
        array_coor = new double[2][countThread];

        thread = new FindLocation[countThread];
        barrier = new CyclicBarrier(countThread);
    }

    public double[] notSimpleFindLocation() {

        for (int id = 0; id < countThread; id++) {
            thread[id] = new FindLocation(angle, length, array_angl, array_coor,
                                          phase, wait, id, segmLength, countThread, barrier);
            thread[id].start();
        }

        for (int id = 0; id < countThread; id++) {
            try {
                thread[id].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double x = array_coor[X][countThread - 1];
        double y = array_coor[Y][countThread - 1];
        double temp = Math.sqrt(x * x + y * y);
        double r = Math.round(temp);

        double alpha = array_angl[countThread - 1];

        return new double[]{alpha, r};
    }

    public double[] simpleFindLocation() {

        double x     = Math.round(length[0] * Math.cos(Math.toRadians(angle[0])));
        double y     = Math.round(length[0] * Math.sin(Math.toRadians(angle[0])));
        double alpha = angle[0];
        for (int i = 1; i < lenArr; i++) {
            alpha = (alpha + angle[i]) % 360;
            x = x + Math.round(length[i] * Math.cos(Math.toRadians(angle[i])));
            y = y + Math.round(length[i] * Math.sin(Math.toRadians(angle[i])));
        }

        double temp = Math.sqrt(x * x + y * y);
        double r = Math.round(temp);

        return new double[]{alpha, r};
    }
}
