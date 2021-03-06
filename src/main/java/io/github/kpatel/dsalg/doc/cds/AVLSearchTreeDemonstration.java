
package io.github.kpatel.dsalg.doc.cds;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class AVLSearchTreeDemonstration extends Demonstration {
    public AVLSearchTreeDemonstration() {
        super("AVL Search Tree", "avl_search_tree.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
