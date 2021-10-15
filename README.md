# Lecteur de fichier

A simple project to learn making GUI with JavaFX.

## Usage & Installation
JavaFX lib is needed to run the project.

The following VM options need to be included, else Illegal Access exceptions will arise. Replace the path with the path to JavaFX files in your computer.
```
--module-path "path\to\JavaFX Lecteur de fichier\lib\javafx-sdk-17.0.0.1\lib" 
--add-modules 
javafx.controls,javafx.fxml,javafx.web --add-exports javafx.web/com.sun.javafx.webkit=ALL-UNNAMED
```

A jar is also available in the "Release" section. To run it:

```
java --module-path "path\to\JavaFX Lecteur de fichier\lib\javafx-sdk-17.0.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.web --add-exports javafx.web/com.sun.javafx.webkit=ALL-UNNAMED -jar "JavaFX Lecteur de fichier.jar"
```


## License
[MIT](https://choosealicense.com/licenses/mit/)