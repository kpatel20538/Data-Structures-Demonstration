package io.github.kpatel.dsalg;


import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.video.VideoController;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {
    @FXML private TreeView tableOfContents;
    @FXML private ScrollPane documentView;
    @FXML private VideoController videoController;

    public void loadDemonstration(Demonstration demonstration) throws IOException {
        documentView.setContent(FXMLLoader.load(getClass().getResource(demonstration.fxmlUrl)));
        Pane animationPane = videoController.getAnimationPane();
        animationPane.getChildren().clear();
        Animation animation = demonstration.makeAnimation(animationPane);
        videoController.setAnimation(animation);
    }
}
