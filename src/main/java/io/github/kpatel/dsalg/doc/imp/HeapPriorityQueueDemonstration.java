
package io.github.kpatel.dsalg.doc.imp;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class HeapPriorityQueueDemonstration extends Demonstration {
    public HeapPriorityQueueDemonstration() {
        super("Heap Priority Queue", "heap_priority_queue.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
