package Decoder;

public class Decode {
    private static int line = 1;
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String DecodeSymbol(String str) {

        int outputColumn = symbol(str, 0);
        int inputIndex = symbol(str, 1);
        int inputLine = symbol(str, 2);
        int inputColumn = symbol(str, 3);

        int nameIndex = symbol(str, 4);
        String name = ReadFile.getNamesByIndex(nameIndex);

        String result = "Line: " + line + " Column: " + outputColumn
                + " |      " + inputIndex + "      | "
                + "Line: " + inputLine + " Column: " + inputColumn
                + " | " + name;

        int len = str.length();
        if (str.charAt(len - 1) == ';') {
            line++;
        }

        return result;
    }

    private static int symbol(String value, int index) {
        char ch = value.charAt(index);
        int result = alphabet.indexOf(ch) >> 1;
        return result;
    }
}