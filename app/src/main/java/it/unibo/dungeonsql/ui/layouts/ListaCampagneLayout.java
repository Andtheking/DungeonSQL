package it.unibo.dungeonsql.ui.layouts;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.services.CampagnaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ListaCampagneLayout extends VBox {

    @FXML
    private ListView<Campagna> listaCampagne;

    private String usernameLoggato;
    private CampagnaService campagnaService;
    private Runnable onBack;
    private Runnable onCreaNuova;
    private Consumer<Campagna> onApriScheda;

    public ListaCampagneLayout(String usernameLoggato, Runnable onBack, Runnable onCreaNuova, Consumer<Campagna> onSelezione) {
        this.usernameLoggato = usernameLoggato;
        this.onBack = onBack;
        this.onApriScheda = onSelezione;
        this.onCreaNuova = onCreaNuova;

        this.campagnaService = new CampagnaService();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/listacampagne_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Errore nel caricamento di listacampagne_view.fxml", exception);
        }

        inizializzaLista();
    }

    private void inizializzaLista() {
        List<Campagna> campagneMaster = campagnaService.getCampagneByUsername(usernameLoggato);
        List<Campagna> campagnePlayer = campagnaService.getCampagnaGiocataByUtente(usernameLoggato);
        
        
        ObservableList<Campagna> tutteLeCampagne = FXCollections.observableArrayList();
        tutteLeCampagne.addAll(campagneMaster);
        tutteLeCampagne.addAll(campagnePlayer);
        
        listaCampagne.setItems(tutteLeCampagne);

        
        listaCampagne.setCellFactory(param -> new ListCell<Campagna>() {
            @Override
            protected void updateItem(Campagna campagna, boolean empty) {
                super.updateItem(campagna, empty);

                if (empty || campagna == null || campagna.getId().getNome() == null) {
                    setText(null);
                } else {
                    
                    String ruolo = campagnaService.isMaster(campagna, usernameLoggato) ? "[MASTER]" : "[GIOCATORE]";
                    setText(campagna.getId().getNome() + "  " + ruolo);
                }
            }
        });
    }

    

    @FXML
    private void apriCampagna(ActionEvent event) {
        Campagna selezionata = listaCampagne.getSelectionModel().getSelectedItem();

        if (selezionata != null) {
            System.out.println("Apertura scheda: " + selezionata.getId().getNome());
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
    private void creaNuovaCampagna(ActionEvent event) {
        onCreaNuova.run();
    }

    @FXML
    private void tornaAlMenu(ActionEvent event) {
        if (onBack != null) {
            onBack.run();
        }
    }
}