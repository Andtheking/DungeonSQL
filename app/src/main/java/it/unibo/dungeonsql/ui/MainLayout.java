package it.unibo.dungeonsql.ui;

import it.unibo.dungeonsql.services.SchedaService;
import it.unibo.dungeonsql.ui.controllers.PersonaggioController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.util.concurrent.CompletableFuture;

public class MainLayout extends BorderPane {

    public MainLayout() {
        // 1. Mettiamo la barra dei menu in alto
        setTop(creaMenuBar());

        // 2. Di default, all'avvio, mostriamo una schermata di benvenuto o la barra di ricerca scheda
        setCenter(creaSchermataBenvenutoOCerca());
    }

    private MenuBar creaMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuVisualizza = new Menu("Visualizza");

        MenuItem itemScheda = new MenuItem("Cerca Scheda Personaggio");
        itemScheda.setOnAction(e -> setCenter(creaSchermataBenvenutoOCerca()));

        MenuItem itemRisorse = new MenuItem("Risorse Classe");
        itemRisorse.setOnAction(e -> {
            // Qui in futuro metterai la tua RisorseView
            // setCenter(new RisorseView());
            System.out.println("Carica vista Risorse Classe");
        });

        MenuItem itemMagie = new MenuItem("Magie");
        itemMagie.setOnAction(e -> {
            // setCenter(new MagieView());
            System.out.println("Carica vista Magie");
        });

        menuVisualizza.getItems().addAll(itemScheda, itemRisorse, itemMagie);
        menuBar.getMenus().add(menuVisualizza);

        return menuBar;
    }

    private BorderPane creaSchermataBenvenutoOCerca() {
        BorderPane sp = new BorderPane();
        sp.setPadding(new Insets(20));
        
        TextField schedaIdInput = new TextField();
        schedaIdInput.setPromptText("Inserisci Codice Scheda (es. pg_aragorn) e premi INVIO...");
        sp.setTop(schedaIdInput);

        // Quando premi Invio, interroghiamo il database in background e carichiamo la SchedaView al centro
        schedaIdInput.setOnAction(e -> {
            String codiceScheda = schedaIdInput.getText();
            
            if (codiceScheda != null && !codiceScheda.trim().isEmpty()) {
                SchedaService ss = new SchedaService();

                CompletableFuture.supplyAsync(() -> ss.getSchedaCompleta(codiceScheda))
                    .thenAcceptAsync(schedaDto -> {
                        Platform.runLater(() -> {
                            // Sostituiamo il centro con la vera SchedaView del personaggio!
                            sp.setCenter(new PersonaggioController(schedaDto));
                        });
                    });
            }
        });

        return sp;
    }
}