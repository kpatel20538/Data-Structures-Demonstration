package io.github.kpatel.dsalg.video.builder;


import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;

public class TransitionBuilder {
    protected Transition transition;
    private Pane pane;

    public TransitionBuilder(Transition transition,Pane pane){
        this.transition = transition;
        this.pane = pane;
    }

    public Pane getPane(){
        return pane;
    }
    public Transition build(){
        return transition;
    }
}
