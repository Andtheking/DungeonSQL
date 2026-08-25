package it.unibo.dungeonsql;

import java.util.concurrent.CompletableFuture;

import it.unibo.dungeonsql.models.Combattimento;
import it.unibo.dungeonsql.ui.MainLayout;
import it.unibo.dungeonsql.ui.controllers.CampagnaController;
import it.unibo.dungeonsql.ui.controllers.CombattimentoController;
import it.unibo.dungeonsql.ui.controllers.DiarioController;
import it.unibo.dungeonsql.ui.controllers.LogInController;
import it.unibo.dungeonsql.ui.controllers.PersonaggioController;
import it.unibo.dungeonsql.ui.controllers.SignInController;
import it.unibo.dungeonsql.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private StackPane rootContainer; 

    @Override
    public void start(Stage primaryStage) {

        CompletableFuture.runAsync(() -> {
            System.out.println("Avvio connessione al DB in background...");
            HibernateUtil.getSessionFactory(); 
            System.out.println("Database pronto all'uso!");
        });

        rootContainer = new StackPane();
        
        Scene scene = new Scene(rootContainer, 1000, 700);

        primaryStage.setTitle("DungeonSQL - Gestionale D&D");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        mostraSchermataLogin();
    }

    private void mostraSchermataLogin() {
        LogInController loginView = new LogInController(
            () -> mostraMainLayout(),
            () -> mostraSchermataSignIn() 
        );

        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(loginView);
    }

    private void mostraSchermataSignIn() {
        SignInController signInView = new SignInController(
            () -> mostraMainLayout(),
            () -> mostraSchermataLogin() 
        );

        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(signInView);
    }

    private void mostraMainLayout() {
        CombattimentoController combattimentoController = new CombattimentoController(STYLESHEET_CASPIAN, null, null);

        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(combattimentoController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}