package Decoder;

import Exceptions.ParseErrorException;
import java.io.*;

public class ReadFile {

    private static String version;
    private static String outputFile;
    private static String inputFile;
    private static String[] names;
    private static String[] mappings;

    public static int size;

    public ReadFile(String nameFile) throws FileNotFoundException{
        try {
            readFile(nameFile);
        }
        catch (ParseErrorException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String nameFile) throws FileNotFoundException, ParseErrorException{
        BufferedReader fr = new BufferedReader(new FileReader(nameFile));
        String filter = "[\":,\\[\\]]";

        try {

            setVersion(fr.readLine(), filter);
            setOutputFile(fr.readLine(), filter);
            setInputFile(fr.readLine(), filter);

            setNames(fr.readLine(), filter);
            setMappings(fr.readLine(), filter);

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setVersion(String str, String filter) throws ParseErrorException{
        str = str.replaceAll(filter, "");
        String[] buffer = str.split(" ");

        if (buffer.length == 2) {
            version = buffer[0] + ": " + buffer[1];
        }
        else {
            throw new ParseErrorException();
        }
    }

    public static String getVersion() {
        return version;
    }

    private void setOutputFile(String str, String filter) throws ParseErrorException{
        str = str.replaceAll(filter, "");
        String[] buffer = str.split(" ");

        if (buffer.length == 2) {
            outputFile = "Output file: " + buffer[1];
        }
        else {
            throw new ParseErrorException();
        }
    }

    public static String getOutputFile() {
        return outputFile;
    }

    private void setInputFile(String str, String filter) throws ParseErrorException{
        str = str.replaceAll(filter, "");
        String[] buffer = str.split(" ");

        if (buffer.length == 2) {
            inputFile = "Input file: " + buffer[1];
        }
        else {
            throw new ParseErrorException();
        }
    }

    public static String getInputFile() {
        return inputFile;
    }

    private void setNames(String str, String filter){
        str = str.replaceAll(filter, "");
        String[] buffer = str.split(" ");

        int len = buffer.length;
        names = new String[len - 1];
        for (int i = 1; i < len; i++) {
            names[i - 1] = buffer[i];
        }

        size = names.length;
    }

    public static String getNamesByIndex(int index) {
        if (index < names.length) {
            return names[index];
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void setMappings(String str, String filter) {
        str = str.replaceAll(filter, "");
        String[] buffer = str.split(" ");

        int len = buffer.length;
        mappings = new String[len - 1];
        for (int i = 1; i < len; i++) {
            mappings[i - 1] = Decode.DecodeSymbol(buffer[i]);
        }
    }

    public static String getMappingsByIndex(int index) {
        if (index < mappings.length) {
            return mappings[index];
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
