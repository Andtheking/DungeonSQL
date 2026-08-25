package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.services.SignInService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.function.Consumer;

public class SignInController extends VBox {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private TextField txtMail;
    @FXML private Label lblFeedback;

    private Consumer<String> onSignInSuccess;
    private Runnable onGoToLogin;
    
    private final SignInService signInService = new SignInService();

    public SignInController(Consumer<String> onSignInSuccess, Runnable onGoToLogin) {
        this.onSignInSuccess = onSignInSuccess;
        this.onGoToLogin = onGoToLogin;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/signin_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di signin_view.fxml", e);
        }
    }

    @FXML
    private void handleSignIn() {
        String user = txtUser.getText();
        String password = txtPass.getText();
        String email = txtMail.getText();
        
        if (signInService.registraUtente(user, email, password)) {
            if (onSignInSuccess != null) onSignInSuccess.accept(user);
        } else {
            lblFeedback.setStyle("-fx-text-fill: #c0392b;"); // Rosso errore
            lblFeedback.setText("Username già in uso o campi vuoti!");
        }
    }

    @FXML
    private void handleGoToLogin() {
        if (onGoToLogin != null) onGoToLogin.run();
    }
}