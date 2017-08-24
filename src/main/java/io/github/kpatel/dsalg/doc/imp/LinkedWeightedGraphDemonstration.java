
package io.github.kpatel.dsalg.doc.imp;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class LinkedWeightedGraphDemonstration extends Demonstration {
    public LinkedWeightedGraphDemonstration() {
        super("Linked Weighted Graph", "linked_weighted_graph.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
