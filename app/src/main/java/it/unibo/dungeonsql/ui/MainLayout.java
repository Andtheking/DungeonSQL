package it.unibo.dungeonsql.ui;

import it.unibo.dungeonsql.services.SchedaService;
import it.unibo.dungeonsql.ui.views.SchedaView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainLayout extends BorderPane {

    public MainLayout() {

        setTop(creaMenuBar());

        BorderPane sp = new BorderPane();
        
        TextField schedaIdInput = new TextField();
        sp.setTop(schedaIdInput);
        schedaIdInput.setOnKeyTyped((e) -> {
            SchedaService ss = new SchedaService();
            sp.setCenter(new SchedaView(ss.getSchedaCompleta(schedaIdInput.getText())));
        });
        setCenter(sp);
    }

    private MenuBar creaMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuVisualizza = new Menu("Visualizza");

        MenuItem itemRisorse = new MenuItem("Risorse Classe");

        MenuItem itemMagie = new MenuItem("Magie");

        menuVisualizza.getItems().addAll(itemRisorse, itemMagie);
        menuBar.getMenus().add(menuVisualizza);

        return menuBar;
    }
}