package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.services.CampagnaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CampagnaController extends VBox {

    @FXML private TextField txtNomeCampagna;
    @FXML private TextArea txtDescrizione;
    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    private String loggedUsername;
    private Runnable onCampagnaCreated;
    private Runnable onCancel;

    // Istanza del service per la comunicazione con il DB
    private final CampagnaService campagnaService = new CampagnaService();

    public CampagnaController(String loggedUsername, Runnable onCampagnaCreated, Runnable onCancel) {
        this.loggedUsername = loggedUsername;
        this.onCampagnaCreated = onCampagnaCreated;
        this.onCancel = onCancel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/campagna_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di creazione_campagna_view.fxml", e);
        }
    }

    @FXML
    private void handleCreaCampagna() {
        lblError.setText("");
        lblSuccess.setText("");

        // Passiamo direttamente loggedUsername al DB
        boolean successo = campagnaService.creaCampagna(loggedUsername, txtNomeCampagna.getText(), txtDescrizione.getText());

        if (successo) {
            lblSuccess.setText("Campagna creata con successo!");
            txtNomeCampagna.clear();
            txtDescrizione.clear();
            if (onCampagnaCreated != null) onCampagnaCreated.run();
        } else {
            lblError.setText("Errore di creazione su PostgreSQL.");
        }
    }

    @FXML
    private void handleAnnulla() {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private void svuotaCampi() {
        txtNomeCampagna.clear();
        txtDescrizione.clear();
        // L'username potrebbe rimanere pre-compilato se l'utente sta creando più campagne
    }
}