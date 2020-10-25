package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final String javaVersion = System.getProperty("java.version");
        final String javafxVersion = System.getProperty("javafx.version");
        System.out.println("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        URL uiFxmlURL = new File("src/main/java/sample/ui.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(uiFxmlURL);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
