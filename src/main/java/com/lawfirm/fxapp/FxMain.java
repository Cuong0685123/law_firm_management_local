package com.lawfirm.fxapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.lawfirm.lawfirm_backend.LawfirmBackendApplication;

public class FxMain extends Application {
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(LawfirmBackendApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello từ JavaFX + Spring Boot!");
        Scene scene = new Scene(label, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Lawfirm Desktop App");
        stage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
