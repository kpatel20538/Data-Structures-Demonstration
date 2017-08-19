package io.github.kpatel.dsalg;

import io.github.kpatel.dsalg.video.VideoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("FXML TableView Example");
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainapp_view.fxml"));
        SplitPane mainView = mainLoader.load();
        MainController mainController = mainLoader.getController();

        FXMLLoader videoLoader = new FXMLLoader(getClass().getResource("video/video_view.fxml"));
        BorderPane videoView = videoLoader.load();
        VideoController videoController = videoLoader.getController();
        mainController.setVideo(videoView);


        Scene myScene = new Scene(mainView);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
