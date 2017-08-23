package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.doc.adt.*;
import io.github.kpatel.dsalg.doc.alg.SearchDemonstration;
import io.github.kpatel.dsalg.doc.alg.SortingDemonstration;
import io.github.kpatel.dsalg.doc.alg.sort.BubbleSortDemonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class AlgorithmsDemonstration extends Demonstration {
    public AlgorithmsDemonstration() {
        super("Algorithms", "algorithms.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new SearchDemonstration().getItem(),
                new SortingDemonstration().getItem()
        );
        return root;
    }


    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
