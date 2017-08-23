package io.github.kpatel.dsalg.view.video.animate.prims;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;

public abstract class DotGroup {
    private final DoubleProperty x;
    private final DoubleProperty y;
    private final DoubleProperty width;
    private final DoubleProperty height;
    private List<Dot> dots;

    public DotGroup(Pane animationPane, double x, double y, double width, double height, int size) {
        this.x = new SimpleDoubleProperty(0);
        this.y = new SimpleDoubleProperty(0);
        this.width = new SimpleDoubleProperty(0);
        this.height = new SimpleDoubleProperty(0);
        this.x.bind(animationPane.widthProperty().multiply(x));
        this.y.bind(animationPane.heightProperty().multiply(y));
        this.width.bind(animationPane.widthProperty().multiply(width));
        this.height.bind(animationPane.heightProperty().multiply(height));
        this.dots = assignDots(size);
    }

    protected abstract List<Dot> assignDots(int size);

    public List<Dot> getDots() {
        return dots;
    }

    public Transition swapDots(int i, int j) {
        Dot left = getDots().get(i);
        Dot right = getDots().get(j);
        Transition ltt = left.getNode().map(node -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), node);
            tt.fromXProperty().bind(left.xProperty());
            tt.fromYProperty().bind(left.yProperty());
            tt.toXProperty().bind(right.xProperty());
            tt.toYProperty().bind(right.yProperty());
            return (Transition) tt;
        }).orElse(new PauseTransition(Duration.ZERO));
        Transition rtt = right.getNode().map(node -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), node);
            tt.fromXProperty().bind(right.xProperty());
            tt.fromYProperty().bind(right.yProperty());
            tt.toXProperty().bind(left.xProperty());
            tt.toYProperty().bind(left.yProperty());
            return (Transition) tt;
        }).orElse(new PauseTransition(Duration.ZERO));
        left.setNode(right.setNode(left.getNode()));
        return new ParallelTransition(ltt, rtt);
    }

    public Transition fadeIn() {
        ParallelTransition pt = new ParallelTransition();
        for (Dot dot : getDots()) {
            pt.getChildren().add(dot.getNode().map(node -> {
                TranslateTransition tt = new TranslateTransition(Duration.ONE, node);
                tt.toXProperty().bind(dot.xProperty());
                tt.toYProperty().bind(dot.yProperty());
                return (Transition) tt;
            }).orElse(new PauseTransition(Duration.ZERO)));
        }
        return pt;
    }

    public Transition moveMarker(Node node,int i,double xOffset,double yOffset){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1), node);
        tt.toXProperty().bind(getDots().get(i).xProperty().add(xOffset));
        tt.toYProperty().bind(getDots().get(i).yProperty().add(yOffset));
        return tt;
    }


    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s,%s,%s) %s", getX(), getY(), getWidth(), getHeight(), getDots());
    }
}
