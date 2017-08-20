package io.github.kpatel.dsalg;


import io.github.kpatel.dsalg.doc.*;
import io.github.kpatel.dsalg.video.VideoController;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {
    @FXML private TreeView<Demonstration> tableOfContents;
    @FXML private ScrollPane documentView;
    @FXML private VideoController videoController;

    public void loadDemonstration(Demonstration demonstration) {
        try{
            documentView.setContent(FXMLLoader.load(demonstration.getFxmlPath()));
            Pane animationPane = videoController.getAnimationPane();
            animationPane.getChildren().clear();
            Animation animation = demonstration.makeAnimation(animationPane);
            videoController.setAnimation(animation);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void init(){
        initTree();
        loadDemonstration(new IntroductionDemonstration());
    }

    public void initTree(){
        tableOfContents.setCellFactory(demonstrationTreeView ->
                new TreeCell<Demonstration>(){
                    @Override protected void updateItem(Demonstration demonstration, boolean empty) {
                        super.updateItem(demonstration, empty);
                        if (!empty && demonstration != null) {
                            setText(demonstration.getName());
                            setOnMouseClicked(event -> loadDemonstration(demonstration));
                        } else {
                            setText(null);
                            setOnMouseClicked(event -> {});
                        }
                    }
                }
        );
        tableOfContents.setRoot(new IntroductionDemonstration().getItem());
    }
}
