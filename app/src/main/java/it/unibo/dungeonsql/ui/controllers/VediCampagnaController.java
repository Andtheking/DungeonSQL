package it.unibo.dungeonsql.ui.controllers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Scheda;
import it.unibo.dungeonsql.services.CampagnaService;
import it.unibo.dungeonsql.services.SchedaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VediCampagnaController extends VBox {
    
    @FXML private VBox vbxPg;
    @FXML private TextField txtMaster;
    @FXML private TextField txtPg;
    @FXML private TextArea txtDescrizione;
    @FXML private Label lblError;
    @FXML private Label lblSuccess;
    @FXML private Label lblTitle;
    @FXML private Button btnNuovaSessione;

    private Campagna campagna;
    private Runnable onBack;
    private Runnable onCreaCampagna;
    private CampagnaService cs = new CampagnaService();
    private SchedaService ss = new SchedaService();
    private Consumer<Campagna> onVediSessioni;
    private Consumer<Campagna> onNuovaSessione;

    public VediCampagnaController(Campagna campagna, String username, Runnable onBack, Runnable onCreaCampagna, Consumer<Campagna> onVediSessioni, Consumer<Campagna> onNuovaSessione) {
        this.campagna = campagna;
        this.onBack = onBack;
        this.onCreaCampagna = onCreaCampagna;
        this.onVediSessioni = onVediSessioni;    
        this.onNuovaSessione = onNuovaSessione;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/vedicampagna_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di signin_view.fxml", e);
        }

        lblTitle.setText(lblTitle.getText() + campagna.getId().getNome());
        
        txtMaster.setText(campagna.getId().getUsernameMaster());

        

        if (cs.isMaster(campagna, username)) {
            vbxPg.setVisible(false);
        } else {
            btnNuovaSessione.setVisible(false);
            List<Scheda> pgs = ss.getSchedeByUtente(username).stream()
                .filter(s -> s.getCampagna().getId().equals(campagna.getId()))
                .toList();

            txtPg.setText(pgs.stream().map(s -> s.getNome()).reduce("", (s1, s2) -> s1 + ", " + s2).substring(2));
        }
        
        txtDescrizione.setText(campagna.getDescrizione() + "\n\nCreata il " + campagna.getDataInizio().toString());
        
    }

    @FXML 
    private void handleNuovaSessione() {
        onNuovaSessione.accept(this.campagna);
    }

    @FXML
    private void handleVediSessioni() {
        onVediSessioni.accept(this.campagna);
    }

    @FXML 
    private void handleIndietro() {
        onBack.run();
    }

    @FXML 
    private void handleCreaCampagna() {
        onCreaCampagna.run();
    }

}
