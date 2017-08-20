package io.github.kpatel.dsalg.video.builder;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SequenceBuilder extends TransitionBuilder{
    public SequenceBuilder(Pane pane){
        super(new SequentialTransition(),pane);
    }

    public SequentialTransition getTransition(){
        return (SequentialTransition) this.transition;
    }

    public SequenceBuilder setNode(Node node){
        getTransition().setNode(node);
        return this;
    }

    public SequenceBuilder followedBy(Animation animation){
        getTransition().getChildren().add(animation);
        return this;
    }
    public SequenceBuilder followedBy(String name,Animation animation){
        getTransition().getCuePoints().put(name,getTransition().getTotalDuration());
        getTransition().getChildren().add(animation);
        return this;
    }
}
