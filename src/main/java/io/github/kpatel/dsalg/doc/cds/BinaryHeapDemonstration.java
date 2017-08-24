
package io.github.kpatel.dsalg.doc.cds;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class BinaryHeapDemonstration extends Demonstration {
    public BinaryHeapDemonstration() {
        super("Binary Heap", "binary_heap.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
