package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.doc.adt.*;
import io.github.kpatel.dsalg.doc.alg.sort.BubbleSortDemonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class AlgorithmsDemonstation  extends Demonstration {
    public AlgorithmsDemonstation(String name, String fxmlPath) {
        super("Algorithms", "algorithms.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new BubbleSortDemonstration().getItem(),
                new ListDemonstration().getItem(),
                new StackDemonstration().getItem(),
                new QueueDemonstration().getItem(),
                new PriorityQueueDemonstration().getItem(),
                new SetDemonstration().getItem(),
                new MapDemonstration().getItem(),
                new AbstractTreeDemonstration().getItem(),
                new GraphDemonstration().getItem(),
                new WeightedGraphDemonstration().getItem()
        );
        return root;
    }


    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
