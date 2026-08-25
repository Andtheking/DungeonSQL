package it.unibo.dungeonsql.ui.controllers;

import it.unibo.dungeonsql.services.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class LogInController extends VBox {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPass;
    @FXML private Label lblError;

    private Runnable onLoginSuccess;
    private Runnable onGoToSignIn;
    
    private final LoginService loginService = new LoginService();

    public LogInController(Runnable onLoginSuccess, Runnable onGoToSignIn) {
        this.onLoginSuccess = onLoginSuccess;
        this.onGoToSignIn = onGoToSignIn;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unibo/dungeonsql/ui/views/login_view.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento di login_view.fxml", e);
        }
    }

    @FXML
    private void handleLogin() {
        String user = txtUser.getText();
        String password = txtPass.getText();
        
        if (loginService.autentica(user, password)) {
            if (onLoginSuccess != null) onLoginSuccess.run();
        } else {
            lblError.setText("Username o password errati!");
        }
    }

    @FXML
    private void handleGoToSignIn() {
        if (onGoToSignIn != null) onGoToSignIn.run();
    }
}