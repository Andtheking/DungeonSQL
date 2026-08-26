package it.unibo.dungeonsql.ui.components;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class NCEComboBox extends ComboBox<OpzioneCompetenza> {
    public NCEComboBox() {
        setItems(FXCollections.observableArrayList(
            new OpzioneCompetenza("N", "Nessuna"),
            new OpzioneCompetenza("C", "Competente"),
            new OpzioneCompetenza("E", "Esperto")
        ));
    }
}
