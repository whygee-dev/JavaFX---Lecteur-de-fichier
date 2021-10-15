package fileprocessing;

public abstract class File implements fileprocessing.Readable {
    protected String filePath;
    protected String content = "";

    public File(String filePath) {
        this.filePath = filePath;
    }

    public String toString() {
        return filePath;
    }
}
