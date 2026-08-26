package it.unibo.dungeonsql;

import java.util.concurrent.CompletableFuture;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Sessione;
import it.unibo.dungeonsql.ui.controllers.CreaCampagnaController;
import it.unibo.dungeonsql.ui.controllers.CreaPersonaggioController;
import it.unibo.dungeonsql.ui.controllers.LogInController;
import it.unibo.dungeonsql.ui.controllers.NuovaSessioneController;
import it.unibo.dungeonsql.ui.controllers.SchedaController;
import it.unibo.dungeonsql.ui.controllers.SignInController;
import it.unibo.dungeonsql.ui.controllers.VediCampagnaController;
import it.unibo.dungeonsql.ui.controllers.VediSessioneController;
import it.unibo.dungeonsql.ui.layouts.ListaCampagneLayout;
import it.unibo.dungeonsql.ui.layouts.ListaSchedeLayout;
import it.unibo.dungeonsql.ui.layouts.ListaSessioniLayout;
import it.unibo.dungeonsql.ui.layouts.MenuLayout;
import it.unibo.dungeonsql.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private StackPane rootContainer; 
    private String loggedInUsername;

    @Override
    public void start(Stage primaryStage) {

        CompletableFuture.runAsync(() -> {
            System.out.println("Avvio connessione al DB in background...");
            HibernateUtil.getSessionFactory(); 
            System.out.println("Database pronto all'uso!");
        });

        rootContainer = new StackPane();
        
        Scene scene = new Scene(rootContainer, 1000, 700);

        primaryStage.setTitle("DungeonSQL - Gestionale D&D");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        mostraSchermataLogin();
    }

    private void mostraSchermataLogin() {
        LogInController loginView = new LogInController(
            (u) -> {
                this.loggedInUsername = u;
                mostraMenuLayout();
            },
            () -> mostraSchermataSignIn() 
        );

        replaceMainNode(loginView);
    }

    private void mostraSchermataSignIn() {
        this.loggedInUsername = null;

        SignInController signInView = new SignInController(
            (u) -> {
                this.loggedInUsername = u;
                mostraMenuLayout();
            },
            () -> mostraSchermataLogin() 
        );
 
        replaceMainNode(signInView);
    }

    //#region Personaggi
    private void mostraListaSchede() {
        ListaSchedeLayout schedeLayout = new ListaSchedeLayout(this.loggedInUsername, this::mostraMenuLayout, this::mostraScheda, this::creaPersonaggio);

        replaceMainNode(schedeLayout);
    }

    private void mostraScheda(SchedaPersonaggio sp) {
        SchedaController sc = new SchedaController(sp, () -> mostraListaSchede());
        
        replaceMainNode(sc);
    }

    private void creaPersonaggio() {
        CreaPersonaggioController cpc = new CreaPersonaggioController(this.loggedInUsername, null, this::mostraListaSchede);
    
        replaceMainNode(cpc);
    }
    //#endregion


    //#region Campagna
    private void mostraListaCampagne() {
        ListaCampagneLayout cc = new ListaCampagneLayout(loggedInUsername,
            this::mostraMenuLayout,
            this::creaCampagna,
            this::mostraCampagna
        );

        replaceMainNode(cc);

    }

    private void creaCampagna() {
        CreaCampagnaController cc = new CreaCampagnaController(loggedInUsername, null, this::mostraListaCampagne);

        replaceMainNode(cc);
    }

    private void mostraCampagna(Campagna campagna) {
        VediCampagnaController vcc = new VediCampagnaController(
            campagna, 
            loggedInUsername, 
            this::mostraListaCampagne, 
            this::creaCampagna,
            this::mostraListaSessioni,
            this::mostraNuovaSessione
        );

        replaceMainNode(vcc);
    }

    private void mostraNuovaSessione(Campagna campagna) {
        NuovaSessioneController sc = new NuovaSessioneController(
            loggedInUsername, campagna, null, this::mostraCampagna
        );

        replaceMainNode(sc);
    }

    private void mostraListaSessioni(Campagna campagna) {
        ListaSessioniLayout lsl = new ListaSessioniLayout(
            loggedInUsername, 
            campagna,
            this::mostraListaCampagne,
            this::mostraNuovaSessione,
            this::mostraSessione);

            replaceMainNode(lsl);
    }

    private void mostraSessione(Sessione sessione) {
        VediSessioneController vsc = new VediSessioneController(
            loggedInUsername, 
            sessione, 
            null, 
            this::mostraListaSessioni
        );

        replaceMainNode(vsc);
    }

    //#endregion

    private void mostraMenuLayout() {
        MenuLayout mainLayout = new MenuLayout(
            loggedInUsername, 
            this::mostraSchermataLogin, 
            this::mostraListaSchede,
            this::mostraListaCampagne
        );
        
        replaceMainNode(mainLayout);
    }

    private void replaceMainNode(Node replaceWith) {
        rootContainer.getChildren().clear();
        rootContainer.getChildren().add(replaceWith);
    }

    public static void main(String[] args) {
        launch(args);
    }
}