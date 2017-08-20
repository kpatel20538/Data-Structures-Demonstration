package io.github.kpatel.dsalg.doc.adt;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class QueueDemonstration extends Demonstration {
    public QueueDemonstration() {
        super("Queue", "queue.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
