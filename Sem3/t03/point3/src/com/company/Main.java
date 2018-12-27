package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] pairs = new int[2][];

        pairs[0] = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2};
        pairs[1] = new int[]{2, 2, 2, 2, 4, 4, 4, 4, 2, 2, 2, 2, 4, 4, 4, 4};

        //pairs[0] = new int[]{1, 1, 1, 1, 2, 2, 2, 2};
        //pairs[1] = new int[]{2, 2, 2, 2, 4, 4, 4, 4};

        StartProcess process = new StartProcess(pairs, 16, 8);
        System.out.println(process.Calculate());
        System.out.println(process.simpleCalculate());
    }
}
