
package io.github.kpatel.dsalg.doc.cds;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class BinarySearchTreeDemonstration extends Demonstration {
    public BinarySearchTreeDemonstration() {
        super("Binary Search Tree", "binary_search_tree.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
