package it.unibo.dungeonsql;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crea il testo da mostrare
        Label greetingLabel = new Label("Hello World!");

        // Crea il layout e aggiunge il testo
        StackPane root = new StackPane();
        root.getChildren().add(greetingLabel);

        // Crea la scena impostando le dimensioni della finestra (larghezza, altezza)
        Scene scene = new Scene(root, 400, 300);

        // Configura e mostra la finestra
        primaryStage.setTitle("DungeonSQL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lancia l'applicazione JavaFX
        launch(args);
    }
}