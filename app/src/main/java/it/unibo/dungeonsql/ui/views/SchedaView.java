package it.unibo.dungeonsql.ui.views;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.*;
import it.unibo.dungeonsql.ui.components.GenericTableComponent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SchedaView extends VBox {

    public SchedaView(SchedaPersonaggio scheda) {
        super(15);
        setPadding(new Insets(15));

        if (scheda == null || scheda.getPersonaggio() == null) {
            getChildren().add(new Label("Nessuna scheda selezionata o personaggio non trovato."));
            return;
        }

        VBox headerBox = creaHeaderAnagrafico(scheda);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabStats = new Tab("Statistiche", creaTabellaCaratteristiche(scheda));
        
        Tab tabInventario = new Tab("Inventario", creaTabellaInventario(scheda));

        Tab tabMagie = new Tab("Magie", creaTabellaMagie(scheda));

        Tab tabProgressi = new Tab("Classi & Progressi", creaTabellaProgressi(scheda));

        tabPane.getTabs().addAll(tabStats, tabInventario, tabMagie, tabProgressi);

        getChildren().addAll(headerBox, tabPane);
        VBox.setVgrow(tabPane, Priority.ALWAYS);
    }

    private VBox creaHeaderAnagrafico(SchedaPersonaggio scheda) {
        VBox box = new VBox(5);
        Personaggio p = scheda.getPersonaggio();
        
        Label lblTitolo = new Label("Scheda Personaggio: " + p.getCodiceScheda());
        lblTitolo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label lblDettagli = new Label("Allineamento: " + p.getAllineamento() + 
                                      " | HP Attuali: " + p.getHp() + 
                                      " | Esperienza: " + p.getExpAccumulata());
        
        box.getChildren().addAll(lblTitolo, lblDettagli);
        return box;
    }

    private GenericTableComponent<Possesso> creaTabellaCaratteristiche(SchedaPersonaggio scheda) {
        GenericTableComponent<Possesso> table = new GenericTableComponent<>(scheda.getCaratteristiche());
        table.addColumn("Caratteristica", p -> p.getId().getNomeCaratteristica());
        table.addColumn("Punteggio", p -> String.valueOf(p.getPunteggio()));
        table.addColumn("Competenza Salvezza", p -> String.valueOf(p.getCompetenzaSalvezza()));
        return table;
    }

    private GenericTableComponent<Object[]> creaTabellaInventario(SchedaPersonaggio scheda) {
        // Ricorda che l'inventario nel DTO era un Object[] (Nome, Quantità, Tipo, Peso, Descrizione)
        GenericTableComponent<Object[]> table = new GenericTableComponent<>(scheda.getInventario());
        table.addColumn("Nome Oggetto", row -> (String) row[0]);
        table.addColumn("Quantità", row -> String.valueOf(row[1]));
        table.addColumn("Tipo", row -> (String) row[2]);
        table.addColumn("Peso", row -> String.valueOf(row[3]));
        table.addColumn("Descrizione", row -> (String) row[4]);
        return table;
    }

    private GenericTableComponent<Magia> creaTabellaMagie(SchedaPersonaggio scheda) {
        GenericTableComponent<Magia> table = new GenericTableComponent<>(scheda.getMagie());
        table.addColumn("Nome Magia", Magia::getNome);
        table.addColumn("Livello", m -> String.valueOf(m.getLivello()));
        table.addColumn("Descrizione", Magia::getDescrizione); // Adatta al tuo getter se diverso
        return table;
    }

    private GenericTableComponent<Progresso> creaTabellaProgressi(SchedaPersonaggio scheda) {
        GenericTableComponent<Progresso> table = new GenericTableComponent<>(scheda.getProgressi());
        table.addColumn("Nome Classe", pr -> pr.getId().getNomeClasse());
        table.addColumn("Livello", pr -> String.valueOf(pr.getLivello()));
        table.addColumn("Sottoclasse", pr -> pr.getSottoclasse().getId().getNomeSottoclasse());
        return table;
    }
}