package io.github.kpatel.dsalg;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;

public class MainController {
    @FXML private TreeView tableOfContents;
    @FXML private ScrollPane documentView;
    @FXML private StackPane videoView;

    public void setVideo(Node n){
        System.out.println("Setting Video");
        this.videoView.getChildren().clear();
        this.videoView.getChildren().add(n);
    }
}
