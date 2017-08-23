package io.github.kpatel.dsalg;


import io.github.kpatel.dsalg.doc.*;
import io.github.kpatel.dsalg.view.video.VideoController;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    @FXML private Button expandButton;
    @FXML private Button collapseButton;
    @FXML private TreeView<Demonstration> contentNavigator;
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
        contentNavigator.setCellFactory(demonstrationTreeView ->
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
        contentNavigator.setRoot(root.getItem());
        expandButton.setOnAction(actionEvent -> expandNavigator(true));
        collapseButton.setOnAction(actionEvent -> expandNavigator(false));
        return root;
    }

    public void expandNavigator(boolean expand){
        ArrayList<TreeItem<Demonstration>> items = new ArrayList<>();
        TreeItem<Demonstration> item;
        items.add(contentNavigator.getRoot());
        while(!items.isEmpty()){
            item = items.remove(0);
            items.addAll(item.getChildren());
            item.setExpanded(expand);
        }
    }
}
