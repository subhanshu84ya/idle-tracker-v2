package com.example.idletracker.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardView {

    private final Stage stage;
    private int seconds = 0;
    private ScheduledExecutorService scheduler;

    public DashboardView(Stage stage) {
        this.stage = stage;
    }

    public void show() {

        Label timerLabel = new Label("00:00");

        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");

        startTimer(timerLabel);

        startButton.setOnAction(e -> {
            System.out.println("Start button clicked");
        });

        stopButton.setOnAction(e -> {
            if (scheduler != null) {
                scheduler.shutdownNow();
            }
        });

        VBox layout = new VBox(15, timerLabel, startButton, stopButton);
        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 300, 200));
        stage.setTitle("Dashboard");
        stage.show();
    }

    private void startTimer(Label label) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            seconds++;
            Platform.runLater(() ->
                    label.setText(formatTime(seconds))
            );
        }, 0, 1, TimeUnit.SECONDS);
    }

    private String formatTime(int totalSeconds) {
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d", mins, secs);
    }
}
