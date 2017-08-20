package io.github.kpatel.dsalg.video;


import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TranslateBuilder {
    private TranslateTransition transition;
    private Pane pane;

    public TranslateBuilder(Pane pane){
        this.transition = new TranslateTransition();
        this.pane = pane;
    }
    public TranslateBuilder setNode(Node node){
        this.transition.setNode(node);
        return this;
    }

    public TranslateBuilder setDuration(Duration duration){
        this.transition.setDuration(duration);
        return this;
    }

    public TranslateBuilder moveBy(double x, double y){
        this.transition.byXProperty().bind(pane.widthProperty().multiply(x));
        this.transition.byYProperty().bind(pane.heightProperty().multiply(y));
        return this;
    }

    public TranslateBuilder moveFrom(double x, double y){
        this.transition.fromXProperty().bind(pane.widthProperty().multiply(x));
        this.transition.fromYProperty().bind(pane.heightProperty().multiply(y));
        return this;
    }

    public TranslateBuilder moveTo(double x, double y){
        this.transition.toXProperty().bind(pane.widthProperty().multiply(x));
        this.transition.toYProperty().bind(pane.heightProperty().multiply(y));
        return this;
    }

    public TranslateTransition build(){
        return transition;
    }
}
