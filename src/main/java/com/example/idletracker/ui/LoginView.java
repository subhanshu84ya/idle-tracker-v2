package com.example.idletracker.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private final Stage stage;

    // Hardcoded credentials
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public LoginView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        Label errorLabel = new Label();

        loginButton.setOnAction(e -> {
            if (USERNAME.equals(usernameField.getText()) &&
                    PASSWORD.equals(passwordField.getText())) {

                new DashboardView(stage).show();
            } else {
                errorLabel.setText("Invalid credentials");
            }
        });

        VBox layout = new VBox(10,
                usernameField,
                passwordField,
                loginButton,
                errorLabel
        );

        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 300, 200));
        stage.setTitle("Login");
        stage.show();
    }
}
