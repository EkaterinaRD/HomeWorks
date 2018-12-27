package Decoder;

import java.io.*;

public class WriteFile {

    public WriteFile(String nameFile) {
        writeFile(nameFile);
    }

    private void writeFile(String nameFile) {
        try {
            File file = new File(nameFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter fw = new PrintWriter(file);

            fw.println(ReadFile.getVersion());
            fw.println(ReadFile.getOutputFile());
            fw.println(ReadFile.getInputFile());

            fw.println("    Output file   | Input index |     Input file    | Name");
            fw.println("------------------------------------------------------------");

            int len = ReadFile.size;
            for (int i = 0; i < len; i++) {
                fw.println(ReadFile.getMappingsByIndex(i));
            }

            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
