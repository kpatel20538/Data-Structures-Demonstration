package io.github.kpatel.dsalg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane(new Button("Open"));
        Scene scene = new Scene(borderPane,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
