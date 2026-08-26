package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Sessione;
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

public class VediSessioneController extends VBox {

    @FXML private TextField txtNomeCampagna;
    @FXML private DatePicker dpDataSessione;
    @FXML private TextArea txtDiario;
    
    @FXML private TextField txtTagScheda;
    @FXML private TextField txtTagOggetto;
    @FXML private TextField txtTagMagia;
    
    @FXML private Label lblTitle;


    private Runnable onSaveSuccess;
    private Consumer<Campagna> onCancel;
    private String loggedInUsername;
    private Sessione sessione;

    private final SessioneService diarioService = new SessioneService();

    public VediSessioneController(String loggedInUsername, Sessione sessione, Runnable onSaveSuccess, Consumer<Campagna> onCancel) {
        this.loggedInUsername = loggedInUsername;
        this.onSaveSuccess = onSaveSuccess;
        this.onCancel = onCancel;
        this.sessione = sessione;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/sessione_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di sessione_view.fxml", e);
        }
    }

    @FXML void initialize() {
        
        txtNomeCampagna.setText(sessione.getId().getNomeCampagna());

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

        lblTitle.setText(lblTitle.getText() + sessione.getId().getDataSvolgimento());
        txtDiario.setText(sessione.getDiario());
        dpDataSessione.setValue(sessione.getId().getDataSvolgimento());
    }

    @FXML
    private void handleIndietro() {
        if (onCancel != null) {
            onCancel.accept(sessione.getCampagna());
        }
    }
}
