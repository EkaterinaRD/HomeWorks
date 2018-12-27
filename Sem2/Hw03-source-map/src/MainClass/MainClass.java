package MainClass;

import Decoder.ReadFile;
import Decoder.WriteFile;

import java.io.FileNotFoundException;

public class MainClass {
    public static void main(String[] args) {
        String nameInputFile = "coding/source_map.txt";
        String nameOutputFile = "result decoder/output.txt";

        try {
            ReadFile inputMap = new ReadFile(nameInputFile);
            WriteFile result = new WriteFile(nameOutputFile);
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't open file!");
            e.printStackTrace();
        }
    }
}
