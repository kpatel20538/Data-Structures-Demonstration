package io.github.kpatel.dsalg.doc.alg;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.doc.alg.search.BinarySearchDemonstration;
import io.github.kpatel.dsalg.doc.alg.search.LinearSearchDemonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class SearchDemonstration extends Demonstration {
    public SearchDemonstration() {
        super("Search Algorithms", "search_algorithms.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new LinearSearchDemonstration().getItem(),
                new BinarySearchDemonstration().getItem()
        );
        return root;
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
