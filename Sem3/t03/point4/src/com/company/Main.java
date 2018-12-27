package com.company;

public class Main {

    public static void main(String[] args) {

        String goodStr = "(())(()())((()(()())))()";
        String badStr  = "))))))))))))((((((((((((";

        StartProcess process = new StartProcess(goodStr, 8);
        process.notSimpleCheckString();
        process.simpleCheckString();
    }
}
