package it.unibo.dungeonsql.ui.layouts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MenuLayout extends VBox {

    @FXML
    private Label lblBenvenuto;

    private String usernameLoggato;
    private Runnable onLogout;
    private Runnable onApriSchede; 
    private Runnable onApriCampagne; 

    public MenuLayout(String usernameLoggato, 
        Runnable onLogout, 
        Runnable onApriSchede,
        Runnable onApriCampagne) {
        this.usernameLoggato = usernameLoggato;
        this.onLogout = onLogout;
        this.onApriSchede = onApriSchede; 
        this.onApriCampagne = onApriCampagne;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/menu_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Errore nel caricamento del file menu_view.fxml", exception);
        }

        lblBenvenuto.setText("Benvenuto, " + this.usernameLoggato + "!");
    }

    @FXML
    private void gestisciSchede(ActionEvent event) {
        if (onApriSchede != null) {
            onApriSchede.run();
        }
    }

    @FXML
    private void gestisciCampagne(ActionEvent event) {
        if (onApriCampagne != null) {
            onApriCampagne.run();
        }
    }
    
    @FXML
    private void eseguiLogout(ActionEvent event) {
        if (onLogout != null) {
            onLogout.run(); 
        }
    }
}