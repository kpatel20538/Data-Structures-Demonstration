
package io.github.kpatel.dsalg.doc.imp;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class HashSetDemonstration extends Demonstration {
    public HashSetDemonstration() {
        super("Hash Set", "hash_set.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
