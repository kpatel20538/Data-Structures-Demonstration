
package io.github.kpatel.dsalg.doc.cds;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class SinglyLinkedDemonstration extends Demonstration {
    public SinglyLinkedDemonstration() {
        super("Singly Linked List", "singly_linked.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
