package com.company;

import java.util.concurrent.CyclicBarrier;

public class StartProcess {

    private int countThread, lenStr;
    private int[] int_a, int_b, int_c;
    private int[] sum, carries;

    public StartProcess(String a, String b, int countThread) {
        this.countThread = countThread;
        lenStr = a.length();
        int_a = new int[lenStr];
        int_b = new int[lenStr];
        getArrays(a, b);
    }

    private void getArrays(String str1, String str2) {
        String strA = new StringBuffer(str1).reverse().toString();
        String strB = new StringBuffer(str2).reverse().toString();

        for (int i = 0; i < lenStr; i++) {
            int_a[i] = strA.charAt(i) - '0';
            int_b[i] = strB.charAt(i) - '0';
        }
    }

    public String startSum() {

        Summator[] thread = new Summator[countThread];
        int segmLen = lenStr / countThread;
        sum = new int[lenStr];
        int_c = new int[countThread];
        carries = new int[lenStr];
        CyclicBarrier barrier = new CyclicBarrier(countThread);
        int[] phases = new int[countThread];
        boolean[] readiness = new boolean[countThread];

        for (int id = 0; id < countThread; id++) {
            thread[id] = new Summator(int_a, int_b, int_c, sum, carries, phases, readiness, countThread, segmLen, id, barrier);
            thread[id].start();
        }

        for (int id = 0; id < countThread; id++) {
            try {
                thread[id].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return getResult(sum);
    }

    public String simpleSummator() {

        int[] result = new int[lenStr];
        int carry = 0;
        for (int i = 0; i < lenStr; i++) {
            result[i] = (int_a[i] + int_b[i] + carry) % 10;
            carry = (int_a[i] + int_b[i] + carry) / 10;
        }

        return getResult(result);
    }

    private String getResult(int[] result) {
        String str = String.valueOf(sum[lenStr - 1]);

        for (int i = lenStr - 1; i >= 0; i--) {
            str = str.concat(String.valueOf(result[i]));
        }

        return str;
    }
}
