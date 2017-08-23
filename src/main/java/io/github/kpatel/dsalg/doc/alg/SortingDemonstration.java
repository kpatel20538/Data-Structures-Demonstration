package io.github.kpatel.dsalg.doc.alg;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.doc.alg.sort.BubbleSortDemonstration;
import io.github.kpatel.dsalg.doc.alg.sort.InsertionSortDemonstration;
import io.github.kpatel.dsalg.doc.alg.sort.SelectionSortDemonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class SortingDemonstration extends Demonstration {
    public SortingDemonstration() {
        super("Sorting Algorithms", "sorting_algorithms.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new BubbleSortDemonstration().getItem(),
                new InsertionSortDemonstration().getItem(),
                new SelectionSortDemonstration().getItem()
        );
        return root;
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
