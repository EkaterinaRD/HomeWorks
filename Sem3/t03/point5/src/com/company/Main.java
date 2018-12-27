package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        double[] angle  = {45, 30, 105, 90};
        double[] length = {40, 50, 40, 20};

        StartProcess process = new StartProcess(angle, length, 2);
        double[] result;
        result = process.notSimpleFindLocation();
        System.out.println(Arrays.toString(result));
        result = process.simpleFindLocation();
        System.out.println(Arrays.toString(result));
    }
}
