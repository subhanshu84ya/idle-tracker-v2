package com.example.idletracker;

import com.example.idletracker.ui.JavaFxApp;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdleTrackerApplication {

	public static void main(String[] args) {
		// Launch JavaFX instead of normal Spring Boot console app
		Application.launch(JavaFxApp.class, args);
	}
}

