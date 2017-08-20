package io.github.kpatel.dsalg.video.builder;


import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TranslateBuilder extends TransitionBuilder{
    public TranslateBuilder(Pane pane){
        super(new TranslateTransition(),pane);
    }

    public TranslateTransition getTransition(){
        return (TranslateTransition) this.transition;
    }

    public TranslateBuilder setNode(Node node){
        getTransition().setNode(node);
        return this;
    }

    public TranslateBuilder setDuration(Duration duration){
        getTransition().setDuration(duration);
        return this;
    }

    public TranslateBuilder moveBy(double x, double y){
        getTransition().byXProperty().bind(getPane().widthProperty().multiply(x));
        getTransition().byYProperty().bind(getPane().heightProperty().multiply(y));
        return this;
    }

    public TranslateBuilder moveFrom(double x, double y){
        getTransition().fromXProperty().bind(getPane().widthProperty().multiply(x));
        getTransition().fromYProperty().bind(getPane().heightProperty().multiply(y));
        return this;
    }

    public TranslateBuilder moveTo(double x, double y){
        getTransition().toXProperty().bind(getPane().widthProperty().multiply(x));
        getTransition().toYProperty().bind(getPane().heightProperty().multiply(y));
        return this;
    }
}
