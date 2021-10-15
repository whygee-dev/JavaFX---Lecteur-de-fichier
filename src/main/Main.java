package main;

import com.sun.javafx.webkit.WebConsoleListener;
import fileprocessing.JFC;
import fileprocessing.diff.Diff;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Main extends Application {

    private JFC jfc;
    private Diff diff;
    @Override
    public void start(final Stage stage) {

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        WebConsoleListener.setDefaultListener((webView, message, lineNumber, sourceId) -> {
            System.out.println(message + "[at " + lineNumber + "]");
        });
        webEngine.setJavaScriptEnabled(true);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        webEngine.getLoadWorker().stateProperty()
                .addListener((obs, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        JSObject jsobj = (JSObject) webEngine.executeScript("window");
                        jfc = new JFC(stage);
                        diff = new Diff();
                        jsobj.setMember("JFC", jfc);
                        jsobj.setMember("Diff", diff);
                        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
                        jsobj.setMember("height", screenBounds.getHeight() - 20);
                        jsobj.setMember("width", screenBounds.getWidth());

                        webEngine.executeScript("initialize()");
                    }
                });

        webEngine.load(this.getClass().getResource("/html/index.html").toString());
        StackPane root = new StackPane();
        root.setId("pane");
        root.getChildren().addAll(browser);
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("/css/index.css").toExternalForm());
        stage.setTitle("Lecteur de fichier");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}