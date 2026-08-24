package it.unibo.dungeonsql;

import it.unibo.dungeonsql.ui.MainLayout;
import it.unibo.dungeonsql.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


import org.hibernate.Session;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        testHibernateConnection();

        MainLayout mainLayout = new MainLayout();

        Scene scene = new Scene(mainLayout, 1000, 650);
        primaryStage.setTitle("DungeonSQL - Gestionale D&D");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    private void testHibernateConnection() {
        System.out.println("Tentativo di connessione a Hibernate e validazione dello schema...");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("La connessione va :3");
        } catch (Exception e) {
            System.err.println("ERRORE CRITICO: Impossibile connettersi al database o validare lo schema!");
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        HibernateUtil.shutdown();
        System.out.println("Connessione Hibernate chiusa correttamente.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}