package it.unibo.dungeonsql.ui.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.dungeonsql.services.PersonaggioService;
import it.unibo.dungeonsql.ui.components.NCEComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

// Estendiamo ScrollPane per permettere lo scorrimento del modulo
public class CreaPersonaggioController extends ScrollPane {

    // Campi Scheda & PG
    @FXML private TextField txtNomePersonaggio;
    @FXML private ComboBox<String> cmbTaglia;
    @FXML private Spinner<Integer> spnHp;
    @FXML private Spinner<Integer> spnCa;
    @FXML private TextField txtAllineamento;
    @FXML private ComboBox<String> cmbCampagna;
    @FXML private ComboBox<String> cmbMaster;
    @FXML private ComboBox<String> cmbRazza;
    @FXML private ComboBox<String> cmbBackground;
    @FXML private ComboBox<String> cmbClasse;

    // Statistiche, Classe, Capacità, Equipaggiamento
    @FXML private CheckBox chkCompetenzaForza;
    
    // Messaggi
    @FXML private Label lblError;
    @FXML private Label lblSuccess;
    
    @FXML private NCEComboBox cmbCapacita;
    @FXML private NCEComboBox cmbAcrobazia;
    @FXML private NCEComboBox cmbAddestrareAnimali;
    @FXML private NCEComboBox cmbArcano;
    @FXML private NCEComboBox cmbAtletica;
    @FXML private NCEComboBox cmbFurtivita;
    @FXML private NCEComboBox cmbIndagare;
    @FXML private NCEComboBox cmbInganno;
    @FXML private NCEComboBox cmbIntimidire;
    @FXML private NCEComboBox cmbIntuizione;
    @FXML private NCEComboBox cmbIntrattenere;
    @FXML private NCEComboBox cmbMedicina;
    @FXML private NCEComboBox cmbNatura;
    @FXML private NCEComboBox cmbPercezione;
    @FXML private NCEComboBox cmbPersuasione;
    @FXML private NCEComboBox cmbRapiditaDiMano;
    @FXML private NCEComboBox cmbReligione;
    @FXML private NCEComboBox cmbSopravvivenza;
    @FXML private NCEComboBox cmbStoria;

    @FXML private Spinner<Integer> spnForza;
    @FXML private Spinner<Integer> spnDestrezza;
    @FXML private Spinner<Integer> spnCostituzione;
    @FXML private Spinner<Integer> spnIntelligenza;
    @FXML private Spinner<Integer> spnSaggezza;
    @FXML private Spinner<Integer> spnCarisma;

    @FXML private CheckBox chkCompetenzaSalvezzaForza;
    @FXML private CheckBox chkCompetenzaSalvezzaDestrezza;
    @FXML private CheckBox chkCompetenzaSalvezzaCostituzione;
    @FXML private CheckBox chkCompetenzaSalvezzaIntelligenza;
    @FXML private CheckBox chkCompetenzaSalvezzaSaggezza;
    @FXML private CheckBox chkCompetenzaSalvezzaCarisma;

    // Mappa che associa il nome della skill (esattamente come va salvato nel DB) al suo componente
    private final Map<String, NCEComboBox> mapSkillComboBoxes = new HashMap<>();

    private final Runnable onSaveSuccess;
    private final Runnable onCancel;
    private final String loggedUsername;

    private final PersonaggioService personaggioService = new PersonaggioService();

    public CreaPersonaggioController(String loggedUsername, Runnable onSaveSuccess, Runnable onCancel) {
        this.loggedUsername = loggedUsername;
        this.onSaveSuccess = onSaveSuccess;
        this.onCancel = onCancel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/creapersonaggio_view.fxml"));
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

        // CHIAMATE AL DATABASE PER POPOLARE LE COMBOBOX
        List<String> razze = personaggioService.getAllNomiRazze();
        cmbRazza.setItems(FXCollections.observableArrayList(razze));

        List<String> backgrounds = personaggioService.getAllNomiBackground();
        cmbBackground.setItems(FXCollections.observableArrayList(backgrounds));

        List<String> classi = personaggioService.getAllNomiClassi();
        cmbClasse.setItems(FXCollections.observableArrayList(classi));

        List<String> campagne = personaggioService.getAllNomiCampagne();
        cmbCampagna.setItems(FXCollections.observableArrayList(campagne));

        List<String> master = personaggioService.getAllUsernamesMaster();
        cmbMaster.setItems(FXCollections.observableArrayList(master));

        // Popoliamo la mappa collegando la stringa del DB al rispettivo componente
        mapSkillComboBoxes.put("Acrobazia", cmbAcrobazia);
        mapSkillComboBoxes.put("Addestrare Animali", cmbAddestrareAnimali);
        mapSkillComboBoxes.put("Arcano", cmbArcano);
        mapSkillComboBoxes.put("Atletica", cmbAtletica);
        mapSkillComboBoxes.put("Furtività", cmbFurtivita);
        mapSkillComboBoxes.put("Indagare", cmbIndagare);
        mapSkillComboBoxes.put("Inganno", cmbInganno);
        mapSkillComboBoxes.put("Intimidire", cmbIntimidire);
        mapSkillComboBoxes.put("Intuizione", cmbIntuizione);
        mapSkillComboBoxes.put("Intrattenere", cmbIntrattenere);
        mapSkillComboBoxes.put("Medicina", cmbMedicina);
        mapSkillComboBoxes.put("Natura", cmbNatura);
        mapSkillComboBoxes.put("Percezione", cmbPercezione);
        mapSkillComboBoxes.put("Persuasione", cmbPersuasione);
        mapSkillComboBoxes.put("Rapidità di Mano", cmbRapiditaDiMano);
        mapSkillComboBoxes.put("Religione", cmbReligione);
        mapSkillComboBoxes.put("Sopravvivenza", cmbSopravvivenza);
        mapSkillComboBoxes.put("Storia", cmbStoria);

        // Permette allo ScrollPane di adattarsi dinamicamente
        this.setFitToWidth(true);
    }

    @FXML
    private void handleCreaPersonaggio() {
        lblError.setText(""); lblSuccess.setText("");
        
        // Genera un CodiceScheda univoco per PostgreSQL 
        Map<String, String> valoriSkill = new HashMap<>();
    
        for (Map.Entry<String, NCEComboBox> entry : mapSkillComboBoxes.entrySet()) {
            String nomeSkill = entry.getKey();
            NCEComboBox combo = entry.getValue();
            
            if (combo != null && combo.getValue() != null) {
                valoriSkill.put(nomeSkill, combo.getValue().codice());
            }
        }

        // Esempio nel metodo di salvataggio del Controller:
        Map<String, Integer> punteggiCaratteristiche = Map.of(
            "Forza", spnForza.getValue(),
            "Destrezza", spnDestrezza.getValue(),
            "Costituzione", spnCostituzione.getValue(),
            "Intelligenza", spnIntelligenza.getValue(),
            "Saggezza", spnSaggezza.getValue(),
            "Carisma", spnCarisma.getValue()
        );

        Map<String, Boolean> competenzeSalvezza = Map.of(
            "Forza", chkCompetenzaSalvezzaForza.isSelected(),
            "Destrezza", chkCompetenzaSalvezzaDestrezza.isSelected(),
            "Costituzione", chkCompetenzaSalvezzaCostituzione.isSelected(),
            "Intelligenza", chkCompetenzaSalvezzaIntelligenza.isSelected(),
            "Saggezza", chkCompetenzaSalvezzaSaggezza.isSelected(),
            "Carisma", chkCompetenzaSalvezzaCarisma.isSelected()
        );

        boolean successo = personaggioService.creaPersonaggioCompleto(
            txtNomePersonaggio.getText(), 
            spnHp.getValue(), 
            spnCa.getValue(),
            cmbTaglia.getValue(), 
            loggedUsername, 
            txtAllineamento.getText(), 
            cmbRazza.getValue(),       // <-- Prelevato da ComboBox
            cmbBackground.getValue(),  // <-- Prelevato da ComboBox
            punteggiCaratteristiche, 
            competenzeSalvezza,
            cmbClasse.getValue(),      // <-- Prelevato da ComboBox
            valoriSkill, 
            cmbCampagna.getValue(),    // <-- Prelevato da ComboBox
            cmbMaster.getValue()       // <-- Prelevato da ComboBox
        );

        if (successo) {
            lblSuccess.setText("Creato!");
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
}