package io.github.kpatel.dsalg.video.builder;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Created by kpatel on 8/19/17.
 */
public class ParallelBuilder extends TransitionBuilder {
    public ParallelBuilder(Pane pane) {
        super(new ParallelTransition(), pane);
    }
    public ParallelTransition getTransition(){
        return (ParallelTransition) this.transition;
    }

    public ParallelBuilder setNode(Node node){
        getTransition().setNode(node);
        return this;
    }

    public ParallelBuilder alongBy(Animation animation){
        getTransition().getChildren().add(animation);
        return this;
    }


}
