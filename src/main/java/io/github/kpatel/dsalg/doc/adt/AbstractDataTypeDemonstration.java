package io.github.kpatel.dsalg.doc.adt;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

public class AbstractDataTypeDemonstration extends Demonstration{

    public AbstractDataTypeDemonstration() {
        super("Abstract Data Types", "abstract_data_type.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new ContainerDemonstration().getItem(),
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
