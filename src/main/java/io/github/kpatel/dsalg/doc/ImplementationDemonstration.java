package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.doc.imp.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class ImplementationDemonstration extends Demonstration{
    public ImplementationDemonstration() {
        super("Implementations", "implementation.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new ArrayListDemonstration().getItem(),
                new SinglyLinkedListDemonstration().getItem(),
                new DoublyLinkedListDemonstration().getItem(),
                new LinkedStackDemonstration().getItem(),
                new LinkedQueueDemonstration().getItem(),
                new HeapPriorityQueueDemonstration().getItem(),
                new TreeSetDemonstration().getItem(),
                new HashSetDemonstration().getItem(),
                new HashMapDemonstration().getItem(),
                new LinkedGraphDemonstration().getItem(),
                new ArrayGraphDemonstration().getItem(),
                new LinkedWeightedGraphDemonstration().getItem(),
                new ArrayWeightedGraphDemonstration().getItem()
        );
        return root;
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
