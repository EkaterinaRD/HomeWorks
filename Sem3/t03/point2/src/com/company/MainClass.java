package com.company;

public class MainClass {

    public static void main(String[] args) {

        String a = "11442695037175000593";
        String b = "11166244415259155177";
        int countThread = 4;

        StartProcess process = new StartProcess(a, b, countThread);
        System.out.println(process.startSum());
        System.out.println(process.simpleSummator());
    }
}
