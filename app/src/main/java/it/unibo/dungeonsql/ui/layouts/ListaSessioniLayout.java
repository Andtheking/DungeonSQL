package it.unibo.dungeonsql.ui.layouts;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Sessione;
import it.unibo.dungeonsql.services.CampagnaService;
import it.unibo.dungeonsql.services.SessioneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class ListaSessioniLayout extends VBox {

    @FXML
    private ListView<Sessione> listaSessioni;

    @FXML
    private Button btnNuovaSessione;

    private String usernameLoggato;
    private SessioneService sessioneService;
    private CampagnaService campagnaService;
    private Runnable onBack;
    private Consumer<Campagna> onCreaNuova;
    private Consumer<Sessione> onApriScheda;
    private Campagna campagna;

    public ListaSessioniLayout(
        String usernameLoggato,
        Campagna campagna,
        Runnable onBack, 
        Consumer<Campagna> onCreaNuova, 
        Consumer<Sessione> onSelezione) {
        this.usernameLoggato = usernameLoggato;
        this.onBack = onBack;
        this.onApriScheda = onSelezione;
        this.onCreaNuova = onCreaNuova;
        this.campagna = campagna;

        this.sessioneService = new SessioneService();
        this.campagnaService = new CampagnaService();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/listasessioni_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Errore nel caricamento di listasessioni_view.fxml", exception);
        }

        inizializzaLista();
    }

    private void inizializzaLista() {
        List<Sessione> sessioni = sessioneService.getSessioniByCampagna(this.campagna);

        if (!campagnaService.isMaster(campagna, usernameLoggato)) {
            btnNuovaSessione.setVisible(false);
        }

        ObservableList<Sessione> sessioniObservable = FXCollections.observableArrayList(sessioni);
        listaSessioni.setItems(sessioniObservable);

        
        listaSessioni.setCellFactory(param -> new ListCell<Sessione>() {
            @Override
            protected void updateItem(Sessione sessione, boolean empty) {
                super.updateItem(sessione, empty);

                if (empty || sessione == null || sessione.getId().getDataSvolgimento() == null) {
                    setText(null);
                } else {
                    setText("Sessione del " + sessione.getId().getDataSvolgimento());
                }
            }
        });
    }

    @FXML
    private void apriSessione(ActionEvent event) {
        Sessione selezionata = listaSessioni.getSelectionModel().getSelectedItem();

        if (selezionata != null) {
            System.out.println("Apertura scheda: " + selezionata.getId().getDataSvolgimento());
            onApriScheda.accept(selezionata); 
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nessuna selezione");
            alert.setHeaderText(null);
            alert.setContentText("Seleziona una scheda dalla lista prima di proseguire.");
            alert.showAndWait();
        }
    }

    @FXML
    private void creaNuovaSessione(ActionEvent event) {
        onCreaNuova.accept(campagna);
    }

    @FXML
    private void tornaAlMenu(ActionEvent event) {
        if (onBack != null) {
            onBack.run();
        }
    }
}