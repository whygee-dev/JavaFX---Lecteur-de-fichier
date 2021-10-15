package fileprocessing.diff;

enum operations {
    delete, insert, neutral
}

public class Operation {

    public operations operation;
    public String sequence;

    public Operation(operations ope, String sequence) {
        this.operation = ope;
        this.sequence = sequence;
    }

    public void addToSequence(String s) {
        sequence += s;
    }

    public String toString() {
        return "";
    }

}
