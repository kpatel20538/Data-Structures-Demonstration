package io.github.kpatel.dsalg.doc.adt;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class ContainerDemonstration extends Demonstration {
    public ContainerDemonstration() {
        super("Container", "container.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
