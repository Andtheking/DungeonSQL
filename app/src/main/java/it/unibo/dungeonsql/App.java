package it.unibo.dungeonsql;

import it.unibo.dungeonsql.components.GenericTableComponent;
import it.unibo.dungeonsql.models.RisorsaClasse;
import it.unibo.dungeonsql.models.Utente;
import it.unibo.dungeonsql.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class App extends Application {
    
    Label label = new Label("Benvenuto in DungeonSQL! Connessione OK.");
    StackPane root = new StackPane(label);
    Scene scene = new Scene(root, 400, 300);
    
    @Override
    public void start(Stage primaryStage) {
        testHibernateConnection();

        primaryStage.setTitle("DungeonSQL - Test Hibernate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void testHibernateConnection() {
        System.out.println("Tentativo di connessione a Hibernate e validazione dello schema...");
        
        List<String> output = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // 1. Recuperi i dati dal database
            List<Utente> listaRisorse = session.createQuery("FROM Utente", Utente.class).getResultList();

            // 2. Crei il componente generico specificando il tipo <Utente>
            GenericTableComponent<Utente> tabellaRisorse = new GenericTableComponent<>(listaRisorse);

            // 3. Aggiungi le colonne indicando etichetta e come estrarre il dato
            tabellaRisorse.addColumn("Username", Utente::getUsername);
            tabellaRisorse.addColumn("Email", Utente::getEmail);
            tabellaRisorse.addColumn("Password", Utente::getPassword);

            // 4. Lo piazzi nel tuo layout (es. in un BorderPane)
            root.getChildren().add(tabellaRisorse);
        } catch (Exception e) {
            System.err.println("ERRORE CRITICO: Impossibile connettersi al database o validare lo schema!");
            e.printStackTrace();
        }
        label.setText(output.stream().reduce("", (a, b) -> a + " " + b));
    }

    @Override
    public void stop() {
        // Chiude correttamente la SessionFactory di Hibernate alla chiusura dell'app JavaFX
        HibernateUtil.shutdown();
        System.out.println("Connessione Hibernate chiusa correttamente.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}