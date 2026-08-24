package it.unibo.dungeonsql.ui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Function;

public class GenericTableComponent<T> extends VBox {

    private final TableView<T> tableView;
    private final ObservableList<T> observableData;

    public GenericTableComponent(List<T> datiIniziali) {
        super();
        this.tableView = new TableView<>();
        this.observableData = FXCollections.observableArrayList(datiIniziali);

        tableView.setItems(observableData);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        this.getChildren().add(tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);
    }

    public void addColumn(String titolo, Function<T, String> valueExtractor) {
        TableColumn<T, String> colonna = new TableColumn<>(titolo);
        colonna.setCellValueFactory(cellData -> 
            new SimpleStringProperty(valueExtractor.apply(cellData.getValue()))
        );
        tableView.getColumns().add(colonna);
    }

    public void setDati(List<T> nuoviDati) {
        observableData.setAll(nuoviDati);
    }
}