package io.github.kpatel.dsalg.video.builder;


import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FadeBuilder extends TransitionBuilder{
    public FadeBuilder(Pane pane) {
        super(new FadeTransition(), pane);
    }

    public FadeTransition getTransition(){
        return (FadeTransition) this.transition;
    }

    public FadeBuilder setNode(Node node){
        getTransition().setNode(node);
        return this;
    }

    public FadeBuilder setDuration(Duration duration){
        getTransition().setDuration(duration);
        return this;
    }

    public FadeBuilder fadeBy(double opacity){
        getTransition().setByValue(opacity);
        return this;
    }

    public FadeBuilder moveFrom(double opacity){
        getTransition().setFromValue(opacity);
        return this;
    }

    public FadeBuilder moveTo(double opacity){
        getTransition().setToValue(opacity);
        return this;
    }
}
