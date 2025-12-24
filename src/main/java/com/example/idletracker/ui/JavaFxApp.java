package com.example.idletracker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/login.fxml")
        );

        Scene scene = new Scene(loader.load(), 400, 300);

        stage.setTitle("Idle Tracker Login");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() {
        System.out.println("Application closed. JVM shutting down...");
        System.exit(0);
    }
}
