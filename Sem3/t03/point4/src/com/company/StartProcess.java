package com.company;

import java.util.concurrent.CyclicBarrier;

public class StartProcess {

    private final int LT = 0;
    private final int RT = 1;

    private char[] string;
    private int[] phase;
    private int[][] array_c;
    private int lenStr, segmLength;
    private int countThread;
    private CheckString[] thread;
    private CyclicBarrier barrier;

    public StartProcess(String str, int countThread) {
        string = str.toCharArray();
        lenStr = str.length();
        this.countThread = countThread;

        init();
    }

    private void init() {

        segmLength = lenStr / countThread;
        phase = new int[countThread];
        thread = new CheckString[countThread];
        array_c = new int[2][countThread];
        barrier = new CyclicBarrier(countThread);
    }

    public void notSimpleCheckString() {

        for (int id = 0; id < countThread; id++) {
            thread[id] = new CheckString(string, array_c, phase, id, segmLength, barrier);
            thread[id].start();
        }

        for (int id = 0; id < countThread; id++) {

            try {
                thread[id].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if (array_c[LT][countThread - 1] == 0 && array_c[RT][countThread - 1] == 0) {
            System.out.println("Good String =)");
        } else {
            System.out.println("Bad String =(");
        }
    }

    public void simpleCheckString() {

        int right = 0, left = 0;
        for (int i = 0; i < lenStr; i++) {
            if (string[i] == ')' && right == 0) {
                left++;
            } else if (string[i] == ')') {
                right--;
            } else {
                right++;
            }
        }

        if (left == 0 && right == 0) {
            System.out.println("Good String =)");
        } else {
            System.out.println("Bad String =(");
        }
    }
}
