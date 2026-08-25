package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.services.DiarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class DiarioController extends VBox {

    @FXML private TextField txtNomeCampagna;
    @FXML private DatePicker dpDataSessione;
    @FXML private TextArea txtDiario;
    
    @FXML private TextField txtTagScheda;
    @FXML private TextField txtTagOggetto;
    @FXML private TextField txtTagMagia;
    
    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    private Runnable onSaveSuccess;
    private Runnable onCancel;
    private String loggedUsername;

    private final DiarioService diarioService = new DiarioService();

    public DiarioController(String loggedUsername, Runnable onSaveSuccess, Runnable onCancel) {
        this.loggedUsername = loggedUsername;
        this.onSaveSuccess = onSaveSuccess;
        this.onCancel = onCancel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/diario_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di diario_sessione_view.fxml", e);
        }
    }

    @FXML
    private void handleSalvaDiario() {
        lblError.setText(""); lblSuccess.setText("");
        boolean successo = diarioService.salvaDiarioETag(
            loggedUsername, txtNomeCampagna.getText(), dpDataSessione.getValue(), txtDiario.getText(),
            txtTagScheda.getText(), txtTagOggetto.getText(), txtTagMagia.getText()
        );

        if (successo) {
            lblSuccess.setText("Salvato con successo!");
            if (onSaveSuccess != null) onSaveSuccess.run();
        } else {
            lblError.setText("Errore: la sessione esiste nel DB?");
        }
    }

    @FXML
    private void handleAnnulla() {
        if (onCancel != null) {
            onCancel.run();
        }
    }

    private void svuotaCampiTag() {
        txtTagScheda.clear();
        txtTagOggetto.clear();
        txtTagMagia.clear();
    }
}
