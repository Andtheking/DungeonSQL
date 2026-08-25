package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.services.CombattimentoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CombattimentoController extends VBox {

    // Dati Sessione & Combattimento
    @FXML private TextField txtCampagnaSelezionata;
    @FXML private DatePicker dpDataSessione;
    @FXML private Spinner<Integer> spnNumCombattimento;

    // Dati Partecipante
    @FXML private Spinner<Integer> spnNumeroIstanza;
    @FXML private Spinner<Integer> spnIniziativa;
    @FXML private Spinner<Integer> spnHp;
    
    // Tipo Partecipante e Codice
    @FXML private RadioButton radioPersonaggio;
    @FXML private RadioButton radioMostro;
    @FXML private Label lblCodice;
    @FXML private TextField txtCodiceScheda;

    // Messaggi
    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    private final Runnable onSaveSuccess;
    private final Runnable onCancel;
    private final String loggedUsername;

    private final CombattimentoService combattimentoService = new CombattimentoService();

    public CombattimentoController(String loggedUsername, Runnable onSaveSuccess, Runnable onCancel) {
        this.loggedUsername = loggedUsername;
        this.onSaveSuccess = onSaveSuccess;
        this.onCancel = onCancel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/combattimento_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di creazione_combattimento_view.fxml", e);
        }
    }

    @FXML
    private void initialize() {
        // Logica per aggiornare l'etichetta dinamicamente
        radioPersonaggio.setOnAction(e -> lblCodice.setText("Codice Scheda PG:"));
        radioMostro.setOnAction(e -> lblCodice.setText("Codice Scheda Mostro:"));
    }

    @FXML
    private void handleScegliCampagna() {
        // Qui dovresti aprire una nuova finestra/modal (es. TableView)
        // che fa una SELECT su PostgreSQL per prendere le campagne dove l'utente è Master.
        // Per ora simuliamo il ritorno di una scelta:
        txtCampagnaSelezionata.setText("Curse of Strahd");
    }

    @FXML
    private void handleAvviaCombattimento() {
        lblError.setText(""); lblSuccess.setText("");
        boolean successo = combattimentoService.avviaCombattimento(
            loggedUsername, txtCampagnaSelezionata.getText(), dpDataSessione.getValue(),
            spnNumCombattimento.getValue(), spnNumeroIstanza.getValue(), spnIniziativa.getValue(),
            spnHp.getValue(), radioPersonaggio.isSelected(), txtCodiceScheda.getText()
        );

        if (successo) {
            lblSuccess.setText("Combattimento avviato!");
        } else {
            lblError.setText("Errore: controlla i vincoli di Postgres.");
        }
    }

    @FXML
    private void handleAnnulla() {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private void svuotaCampiPartecipante() {
        txtCodiceScheda.clear();
        spnNumeroIstanza.getValueFactory().setValue(spnNumeroIstanza.getValue() + 1); // Prepara il prossimo ID
    }
}