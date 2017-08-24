
package io.github.kpatel.dsalg.doc.imp;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class LinkedQueueDemonstration extends Demonstration {
    public LinkedQueueDemonstration() {
        super("Linked Queue", "linked_queue.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
