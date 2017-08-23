package io.github.kpatel.dsalg.view.video.animate.builder;

import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TranslateBuilder {
    private TranslateTransition transition;
    private Pane pane;
    public TranslateBuilder(Pane pane) {
        this.transition = new TranslateTransition();
        this.pane = pane;
    }

    public TranslateBuilder setNode(Node node) {
        this.transition.setNode(node);
        return this;
    }

    public TranslateBuilder setDuration(Duration duration) {
        this.transition.setDuration(duration);
        return this;
    }

    public TranslateBuilder setDelay(Duration duration) {
        this.transition.setDelay(duration);
        return this;
    }

    public TranslateBuilder from(double x, double y) {
        this.transition.fromXProperty().bind(this.pane.widthProperty().multiply(x));
        this.transition.fromYProperty().bind(this.pane.heightProperty().multiply(y));
        return this;
    }

    public TranslateBuilder to(double x, double y) {
        this.transition.toXProperty().bind(this.pane.widthProperty().multiply(x));
        this.transition.toYProperty().bind(this.pane.heightProperty().multiply(y));
        return this;
    }

    public TranslateBuilder from(Offscreen offscreen, double v) {
        Bounds textBounds = this.transition.getNode().getBoundsInLocal();
        double textWidth = textBounds.getWidth();
        double textHeight = textBounds.getHeight();
        switch (offscreen) {
            case TOP:
                this.transition.fromXProperty().bind(this.pane.widthProperty().multiply(v));
                this.transition.setFromY(-textHeight);
                break;
            case BOTTOM:
                this.transition.fromXProperty().bind(this.pane.widthProperty().multiply(v));
                this.transition.fromYProperty().bind(this.pane.heightProperty());
                break;
            case LEFT:
                this.transition.setFromX(-textWidth);
                this.transition.fromYProperty().bind(this.pane.heightProperty().multiply(v));
                break;
            case RIGHT:
                this.transition.fromXProperty().bind(this.pane.widthProperty());
                this.transition.fromYProperty().bind(this.pane.heightProperty().multiply(v));
                break;
        }
        return this;
    }

    public TranslateBuilder to(Offscreen offscreen, double v) {
        Bounds textBounds = this.transition.getNode().getBoundsInLocal();
        double textWidth = textBounds.getWidth();
        double textHeight = textBounds.getHeight();
        switch (offscreen) {
            case TOP:
                this.transition.toXProperty().bind(this.pane.widthProperty().multiply(v));
                this.transition.setToY(-textHeight);
                break;
            case BOTTOM:
                this.transition.toXProperty().bind(this.pane.widthProperty().multiply(v));
                this.transition.toYProperty().bind(this.pane.heightProperty());
                break;
            case LEFT:
                this.transition.setToX(-textWidth);
                this.transition.toYProperty().bind(this.pane.heightProperty().multiply(v));
                break;
            case RIGHT:
                this.transition.toXProperty().bind(this.pane.widthProperty());
                this.transition.toYProperty().bind(this.pane.heightProperty().multiply(v));
                break;
        }
        return this;
    }

    public TranslateTransition build() {
        return this.transition;
    }

    public enum Offscreen {
        TOP, BOTTOM, LEFT, RIGHT
    }


}
