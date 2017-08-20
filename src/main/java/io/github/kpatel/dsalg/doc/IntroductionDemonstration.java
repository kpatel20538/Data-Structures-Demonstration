package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.doc.adt.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class IntroductionDemonstration extends Demonstration {
    public IntroductionDemonstration() {
        super("Data Structures and Algorithms", "introduction.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new AbstractDataTypeDemonstration().getItem()
        );
        root.setExpanded(true);
        return root;
    }
}
