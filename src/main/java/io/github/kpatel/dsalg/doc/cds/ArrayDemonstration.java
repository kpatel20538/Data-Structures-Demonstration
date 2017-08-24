
package io.github.kpatel.dsalg.doc.cds;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class ArrayDemonstration extends Demonstration {
    public ArrayDemonstration() {
        super("Array", "array.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
