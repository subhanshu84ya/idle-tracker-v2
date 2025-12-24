package com.example.idletracker.ui;

import com.example.idletracker.nativeapi.IdleTrackerLibrary;
import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DashboardController {

    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
    }


    private void callApis() {

        String payload = """
        {
          "userEmailId": "test@example.com",
          "idleState": true
        }
        """;

        // HTTP API (dummy)
        String httpApi =
                "http://example.com/api/idle";

        // HTTPS API (dummy)
        String httpsApi =
                "https://example.com/api/idle";

        System.out.println("Calling HTTP API: " + httpApi);
        System.out.println("Payload: " + payload);

        System.out.println("Calling HTTPS API: " + httpsApi);
        System.out.println("Payload: " + payload);
    }


    private void showIdleNotification() {
        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.INFORMATION
                );

        alert.setTitle("Idle Detected");
        alert.setHeaderText(null);
        alert.setContentText("Desktop is in idle state");

        alert.show();
    }


    @FXML
    private Label timerLabel;

    private int seconds = 0;
    private Timeline timerTimeline;

    // Background executor for Start/Stop (DLL work later)
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> idleTask;

    // ✅ AUTO-START TIMER AFTER LOGIN
    @FXML
    public void initialize() {
        timerTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    seconds++;
                    timerLabel.setText(formatTime(seconds));
                })
        );
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
        timerTimeline.play();   // 🔥 Auto start
    }

    // 🔘 START BUTTON (idle tracking later)
    @FXML
    public void onStartClicked() {

        if (idleTask == null || idleTask.isDone()) {

            idleTask = executor.submit(() -> {

                System.out.println("Idle tracking started");

                boolean isIdle =
                        IdleTrackerLibrary.INSTANCE.isSystemIdle(10); // 10 sec

                if (isIdle) {
                    Platform.runLater(() -> {
                        showIdleNotification();
                        callApis();
                    });
                }
            });
        }
    }


    // 🔘 STOP BUTTON
    @FXML
    public void onStopClicked() {
        if (idleTask != null) {
            idleTask.cancel(true);
            System.out.println("Idle tracking stopped");
        }
    }

    private String formatTime(int totalSeconds) {
        int hrs = totalSeconds / 3600;
        int mins = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hrs, mins, secs);
    }
}
