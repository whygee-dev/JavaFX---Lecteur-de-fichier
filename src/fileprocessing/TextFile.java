package fileprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class TextFile extends fileprocessing.File {

    public TextFile(String filePath) {
        super(filePath);
    }

    public String readRaw(){
        // close is automatically called if something goes wrong (try-with-resources)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            content = br.lines().collect(Collectors.joining("\n"));

            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public ArrayList<String> readLines(){
        // close is automatically called if something goes wrong (try-with-resources)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return new ArrayList<>(br.lines().collect(Collectors.toList()));
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String formatOutput(boolean reverseByLine, boolean reverseByCharacters) {
        var lines = readLines();
        var output = readRaw();

        if (reverseByLine){
            Collections.reverse(lines);
            output = String.join("\n", lines);
        }

        if (reverseByCharacters) {
            output = new StringBuffer(output).reverse().toString();
        }

        return output;
    }
}
