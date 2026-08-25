package it.unibo.dungeonsql;

import java.util.concurrent.CompletableFuture;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.ui.controllers.LogInController;
import it.unibo.dungeonsql.ui.controllers.SchedaController;
import it.unibo.dungeonsql.ui.controllers.SignInController;
import it.unibo.dungeonsql.ui.layouts.ListaSchedeLayout;
import it.unibo.dungeonsql.ui.layouts.MenuLayout;
import it.unibo.dungeonsql.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private StackPane rootContainer; 
    private String loggedInUsername;

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
            (u) -> {
                this.loggedInUsername = u;
                mostraMenuLayout();
            },
            () -> mostraSchermataSignIn() 
        );

        replaceMainNode(loginView);
    }

    private void mostraSchermataSignIn() {
        this.loggedInUsername = null;

        SignInController signInView = new SignInController(
            (u) -> {
                this.loggedInUsername = u;
                mostraMenuLayout();
            },
            () -> mostraSchermataLogin() 
        );
 
        replaceMainNode(signInView);
    }

    private void mostraListaSchede() {
        ListaSchedeLayout schedeLayout = new ListaSchedeLayout(this.loggedInUsername, this::mostraMenuLayout, this::mostraScheda);

        replaceMainNode(schedeLayout);
    }

    private void mostraScheda(SchedaPersonaggio sp) {
        SchedaController sc = new SchedaController(sp);
        
        replaceMainNode(sc);
    }

    private void mostraMenuLayout() {
        MenuLayout mainLayout = new MenuLayout(loggedInUsername, this::mostraSchermataLogin, this::mostraListaSchede);
        
        replaceMainNode(mainLayout);
    }

    private void replaceMainNode(Node replaceWith) {
        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(replaceWith);
    }

    public static void main(String[] args) {
        launch(args);
    }
}