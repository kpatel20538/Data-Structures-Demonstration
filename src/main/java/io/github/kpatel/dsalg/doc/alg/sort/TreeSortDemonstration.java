
package io.github.kpatel.dsalg.doc.alg.sort;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class TreeSortDemonstration extends Demonstration {
    public TreeSortDemonstration() {
        super("Tree Sort", "tree_sort.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
