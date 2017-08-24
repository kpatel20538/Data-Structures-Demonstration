
package io.github.kpatel.dsalg.doc.alg.sort;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class HeapSortDemonstration extends Demonstration {
    public HeapSortDemonstration() {
        super("Heap Sort", "heap_sort.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
