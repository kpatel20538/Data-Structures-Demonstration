
package io.github.kpatel.dsalg.doc.imp;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class ArrayWeightedGraphDemonstration extends Demonstration {
    public ArrayWeightedGraphDemonstration() {
        super("Array Weighted Graph", "array_weighted_graph.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
