package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.services.SessioneService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class NuovaSessioneController extends VBox {

    @FXML private TextField txtNomeCampagna;
    @FXML private DatePicker dpDataSessione;
    @FXML private TextArea txtDiario;
    
    @FXML private TextField txtTagScheda;
    @FXML private TextField txtTagOggetto;
    @FXML private TextField txtTagMagia;
    
    @FXML private Label lblError;
    @FXML private Label lblSuccess;

    private Runnable onSaveSuccess;
    private Consumer<Campagna> onCancel;
    private String loggedUsername;
    private Campagna campagna;

    private final SessioneService diarioService = new SessioneService();

    public NuovaSessioneController(String loggedUsername, Campagna campagna, Runnable onSaveSuccess, Consumer<Campagna> onCancel) {
        this.loggedUsername = loggedUsername;
        this.onSaveSuccess = onSaveSuccess;
        this.onCancel = onCancel;
        this.campagna = campagna;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/diario_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di diario_view.fxml", e);
        }
    }

    @FXML void initialize() {
        
        txtNomeCampagna.setText(campagna.getId().getNome());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        dpDataSessione.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                }
                return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                }
                return null;
            }
        });
        dpDataSessione.setValue(LocalDate.now());
    }

    @FXML
    private void handleSalvaDiario() {
        lblError.setText(""); lblSuccess.setText("");
        boolean successo = diarioService.salvaDiarioETag(
            loggedUsername, campagna, dpDataSessione.getValue(), txtDiario.getText(),
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
            onCancel.accept(campagna);
        }
    }
}
