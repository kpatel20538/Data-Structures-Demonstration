
package io.github.kpatel.dsalg.doc.alg.search;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class TreeTraversalDemonstration extends Demonstration {
    public TreeTraversalDemonstration() {
        super("Tree Traversal", "tree_traversal.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
