package fileprocessing;

import java.util.ArrayList;

public interface Readable {
    String readRaw();
    ArrayList<String> readLines();
}
