
package io.github.kpatel.dsalg.doc.imp;

import io.github.kpatel.dsalg.doc.Demonstration;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class SinglyLinkedListDemonstration extends Demonstration {
    public SinglyLinkedListDemonstration() {
        super("Singly Linked List", "singly_linked_list.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        return new Timeline();
    }
}
