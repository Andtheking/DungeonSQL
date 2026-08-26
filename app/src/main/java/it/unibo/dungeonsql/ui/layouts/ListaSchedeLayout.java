package it.unibo.dungeonsql.ui.layouts;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.Scheda; 
import it.unibo.dungeonsql.services.SchedaService;

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
import java.util.List;
import java.util.function.Consumer;

public class ListaSchedeLayout extends VBox {

    @FXML
    private ListView<Scheda> listaSchede;

    private String usernameLoggato;
    private SchedaService schedaService;
    private Runnable onBack; // Callback per tornare al menu
    private Runnable onCreaScheda; // Callback per tornare al menu
    private Consumer<SchedaPersonaggio> onApriScheda;

    public ListaSchedeLayout(String usernameLoggato, 
        Runnable onBack, 
        Consumer<SchedaPersonaggio> onApriScheda,
        Runnable onCreaScheda
    
    ) {
        this.usernameLoggato = usernameLoggato;
        this.onBack = onBack;
        this.onApriScheda = onApriScheda;
        this.onCreaScheda = onCreaScheda;

        this.schedaService = new SchedaService(); // O passalo dal costruttore se usi Dependency Injection

        // 1. Caricamento FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/listaschede_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Errore nel caricamento di listaschede_view.fxml", exception);
        }

        // 2. Inizializzazione dati
        inizializzaLista();
    }

    private void inizializzaLista() {
        // Recupera i dati tramite il tuo Service
        List<Scheda> schedeTrovate = schedaService.getSchedeByUtente(usernameLoggato);
        ObservableList<Scheda> schedeObservable = FXCollections.observableArrayList(schedeTrovate);
        
        listaSchede.setItems(schedeObservable);

        // Formatta come le schede appaiono visivamente nella lista
        listaSchede.setCellFactory(param -> new ListCell<Scheda>() {
            @Override
            protected void updateItem(Scheda scheda, boolean empty) {
                super.updateItem(scheda, empty);

                if (empty || scheda == null || scheda.getNome() == null) {
                    setText(null);
                } else {
                    // Testo mostrato: Nome Personaggio - Nome Campagna (es. Aragorn - La Miniera Perduta)
                    setText(scheda.getNome() + "  —  Campagna: " + scheda.getCampagna().getId().getNome());
                }
            }
        });
    }

    // --- Azioni dei Bottoni ---

    @FXML
    private void apriScheda(ActionEvent event) {
        Scheda selezionata = listaSchede.getSelectionModel().getSelectedItem();

        if (selezionata != null) {
            System.out.println("Apertura scheda: " + selezionata.getNome());
            onApriScheda.accept(schedaService.getSchedaCompleta(selezionata.getCodiceScheda()));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nessuna selezione");
            alert.setHeaderText(null);
            alert.setContentText("Seleziona una scheda dalla lista prima di proseguire.");
            alert.showAndWait();
        }
    }

    @FXML
    private void creaNuovaScheda(ActionEvent event) {
        onCreaScheda.run();
    }

    @FXML
    private void tornaAlMenu(ActionEvent event) {
        if (onBack != null) {
            onBack.run();
        }
    }
}