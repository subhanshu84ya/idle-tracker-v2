package com.example.idletracker.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void onLogin() throws Exception {

        // Hardcoded credentials (allowed)
        if ("admin".equals(usernameField.getText()) &&
                "admin123".equals(passwordField.getText())) {

            Stage stage = (Stage) usernameField.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/dashboard.fxml")
            );

            stage.setScene(new Scene(loader.load(), 600, 400));
            stage.setTitle("Idle Tracker Dashboard");
        }
    }
}
