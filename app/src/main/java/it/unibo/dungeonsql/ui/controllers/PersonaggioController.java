package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.services.PersonaggioService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

// Estendiamo ScrollPane per permettere lo scorrimento del modulo
public class PersonaggioController extends ScrollPane {

    // Campi Scheda & PG
    @FXML private TextField txtNomePersonaggio;
    @FXML private ComboBox<String> cmbTaglia;
    @FXML private Spinner<Integer> spnHp;
    @FXML private Spinner<Integer> spnCa;
    @FXML private TextField txtAllineamento;
    @FXML private TextField txtFonNome;
    @FXML private TextField txtAppNome;

    // Statistiche, Classe, Capacità, Equipaggiamento
    @FXML private Spinner<Integer> spnForza;
    @FXML private CheckBox chkCompetenzaForza;
    @FXML private TextField txtNomeClasse;
    @FXML private TextField txtNomeCapacita;
    @FXML private Spinner<Integer> spnLivelloCapacita;
    @FXML private TextField txtCodiceOggetto;

    // Messaggi
    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    private final Runnable onSaveSuccess;
    private final Runnable onCancel;
    private final String loggedUsername;

    private final PersonaggioService personaggioService = new PersonaggioService();

    public PersonaggioController(String loggedUsername, Runnable onSaveSuccess, Runnable onCancel) {
        this.loggedUsername = loggedUsername;
        this.onSaveSuccess = onSaveSuccess;
        this.onCancel = onCancel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/personaggio_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di creazione_personaggio_view.fxml", e);
        }
    }

    @FXML
    private void initialize() {
        // Inizializza la ComboBox della taglia
        cmbTaglia.setItems(FXCollections.observableArrayList("Piccola", "Media", "Grande"));
        cmbTaglia.getSelectionModel().select("Media");
        
        // Permette allo ScrollPane di adattarsi dinamicamente
        this.setFitToWidth(true);
    }

    @FXML
    private void handleCreaPersonaggio() {
        lblError.setText(""); lblSuccess.setText("");
        
        // Genera un CodiceScheda univoco per PostgreSQL 
        String generatoCodice = java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase();

        boolean successo = personaggioService.creaPersonaggioCompleto(
            generatoCodice, txtNomePersonaggio.getText(), spnHp.getValue(), spnCa.getValue(),
            cmbTaglia.getValue(), loggedUsername, txtAllineamento.getText(), txtFonNome.getText(),
            txtAppNome.getText(), spnForza.getValue(), chkCompetenzaForza.isSelected(),
            txtNomeClasse.getText(), txtNomeCapacita.getText(), spnLivelloCapacita.getValue(),
            txtCodiceOggetto.getText()
        );

        if (successo) {
            lblSuccess.setText("Creato! Codice assegnato dal DB: " + generatoCodice);
        } else {
            lblError.setText("Errore di inserimento su Postgres.");
        }
    }

    @FXML
    private void handleAnnulla() {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private void svuotaCampi() {
        txtNomePersonaggio.clear();
        txtAllineamento.clear();
        txtFonNome.clear();
        txtAppNome.clear();
        txtNomeClasse.clear();
        txtNomeCapacita.clear();
        txtCodiceOggetto.clear();
        chkCompetenzaForza.setSelected(false);
    }
}