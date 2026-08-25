package it.unibo.dungeonsql.ui.layouts;

import it.unibo.dungeonsql.services.SchedaService;
import it.unibo.dungeonsql.ui.controllers.SchedaController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.CompletableFuture;

public class MainLayout extends BorderPane {

    private String username;

    public MainLayout(String username) {
        this.username = username;
        setTop(creaMenuBar());
        setCenter(creaSchermataBenvenuto());
    }

    private MenuBar creaMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuVisualizza = new Menu("Visualizza");

        MenuItem itemScheda = new MenuItem("Cerca Scheda Personaggio");
        itemScheda.setOnAction(e -> setCenter(creaSchermataBenvenuto()));

        MenuItem itemRisorse = new MenuItem("Risorse Classe");
        itemRisorse.setOnAction(e -> {
            System.out.println("Carica vista Risorse Classe");
        });

        MenuItem itemMagie = new MenuItem("Magie");
        itemMagie.setOnAction(e -> {
            System.out.println("Carica vista Magie");
        });

        menuVisualizza.getItems().addAll(itemScheda, itemRisorse, itemMagie);
        menuBar.getMenus().add(menuVisualizza);

        return menuBar;
    }

    private BorderPane creaSchermataBenvenuto() {
        BorderPane sp = new BorderPane();
        sp.setPadding(new Insets(20));

        VBox top = new VBox();

        Label welcome = new Label("Benvenuto, " + username);
        top.getChildren().add(welcome);

        TextField schedaIdInput = new TextField();
        schedaIdInput.setPromptText("Inserisci Codice Scheda e premi INVIO...");
        top.getChildren().add(schedaIdInput);
        
        sp.setTop(top);


        schedaIdInput.setOnAction(e -> {
            String codiceScheda = schedaIdInput.getText();
            
            if (codiceScheda != null && !codiceScheda.trim().isEmpty()) {
                SchedaService ss = new SchedaService();

                CompletableFuture.supplyAsync(() -> ss.getSchedaCompleta(Integer.parseInt(codiceScheda)))
                    .thenAcceptAsync(schedaDto -> {
                        Platform.runLater(() -> {
                            sp.setCenter(new SchedaController(schedaDto));
                        });
                    });
            }
        });

        return sp;
    }
}