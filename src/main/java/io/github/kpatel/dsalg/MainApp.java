package io.github.kpatel.dsalg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Data Structures and Algorithms");

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainapp_view.fxml"));
        SplitPane mainView = mainLoader.load();
        MainController mainController = mainLoader.getController();
        Scene myScene = new Scene(mainView);

        primaryStage.setScene(myScene);
        primaryStage.show();

        mainController.init();
    }
}
