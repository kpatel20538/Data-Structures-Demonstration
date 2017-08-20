package io.github.kpatel.dsalg;


import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.doc.TestAnimation;
import io.github.kpatel.dsalg.doc.TestAnimation2;
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
            documentView.setContent(FXMLLoader.load(getClass().getResource(demonstration.fxmlUrl)));
            Pane animationPane = videoController.getAnimationPane();
            animationPane.getChildren().clear();
            Animation animation = demonstration.makeAnimation(animationPane);
            videoController.setAnimation(animation);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void init(){
        tableOfContents.setCellFactory(demonstrationTreeView ->
            new TreeCell<Demonstration>(){
                @Override protected void updateItem(Demonstration demonstration, boolean empty) {
                    super.updateItem(demonstration, empty);
                    if (!empty && demonstration != null) {
                        setText(demonstration.getName());
                        //setGraphic(iconView);
                        //iconView.setImage(pair.getKey().getImage());
                    } else {
                        setText(null);
                        setGraphic(null);
                    }
                    setOnMouseClicked(event -> loadDemonstration(demonstration));
                }
            }
        );

        TreeItem<Demonstration> root = new TreeItem<>();
        TreeItem<Demonstration> ta1 = new TreeItem<>(new TestAnimation());
        TreeItem<Demonstration> ta2 = new TreeItem<>(new TestAnimation2());
        root.getChildren().addAll(ta1,ta2);
        tableOfContents.setRoot(root);


        loadDemonstration(new TestAnimation());

    }
}
