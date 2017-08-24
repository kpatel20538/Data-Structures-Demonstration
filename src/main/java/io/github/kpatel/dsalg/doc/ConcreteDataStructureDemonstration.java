package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.doc.cds.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class ConcreteDataStructureDemonstration extends Demonstration{
    public ConcreteDataStructureDemonstration() {
        super("Concrete Data Structures", "concrete_data_structure.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new ArrayDemonstration().getItem(),
                new SinglyLinkedDemonstration().getItem(),
                new DoublyLinkedDemonstration().getItem(),
                new BinarySearchTreeDemonstration().getItem(),
                new AVLSearchTreeDemonstration().getItem(),
                new BinaryHeapDemonstration().getItem(),
                new HashTableDemonstration().getItem()
        );
        return root;
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
