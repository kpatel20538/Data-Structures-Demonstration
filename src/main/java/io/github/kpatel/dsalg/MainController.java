package io.github.kpatel.dsalg;


import io.github.kpatel.dsalg.doc.*;
import io.github.kpatel.dsalg.video.VideoController;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML private TreeView<Demonstration> tableOfContents;
    @FXML private StackPane documentView;
    @FXML private VideoController videoController;

    public void loadDemonstration(Demonstration demonstration) {
        try{
            documentView.getChildren().add(FXMLLoader.load(demonstration.getFxmlPath()));
            Pane animationPane = videoController.getAnimationPane();
            animationPane.getChildren().clear();
            Animation animation = demonstration.makeAnimation(animationPane);
            videoController.setAnimation(animation);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void init(){
        loadDemonstration(createTableOfContents());
    }

    public Demonstration createTableOfContents(){
        // Setup Tree Cell's Text and onClick Handler
        tableOfContents.setCellFactory(demonstrationTreeView ->
                new TreeCell<Demonstration>(){
                    @Override protected void updateItem(Demonstration demonstration, boolean empty) {
                        super.updateItem(demonstration, empty);
                        boolean isValid = !empty && demonstration != null;
                        setText(isValid ? demonstration.getName():null);
                        setOnMouseClicked(isValid ?
                                (event -> loadDemonstration(demonstration)) :
                                (event -> {})
                        );
                    }
                }
        );
        // Load Demonstrations to TreeView
        IntroductionDemonstration root = new IntroductionDemonstration();
        tableOfContents.setRoot(root.getItem());
        return root;
    }
}
