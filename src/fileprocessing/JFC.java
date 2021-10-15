package fileprocessing;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class JFC {
    private TextFile selectedFile = null;
    private final Stage primaryStage;
    final FileChooser fileChooser;

    public JFC(Stage primaryStage) {
        this.primaryStage = primaryStage;

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte (*.txt)", "*.txt"));
    }

    public TextFile openTextFile(boolean currentFile, boolean save) {
        File file = null;

        if (!currentFile || selectedFile == null) {
            file = fileChooser.showOpenDialog(primaryStage);

            if (save)
                selectedFile = new TextFile(file.getAbsolutePath());
        }

        return file != null ? new TextFile(file.getAbsolutePath()) : selectedFile;
    }

}
