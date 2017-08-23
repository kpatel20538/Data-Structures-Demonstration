package io.github.kpatel.dsalg.view.video.animate.prims;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Optional;

public class Dot {
    private final DoubleProperty x;
    private final DoubleProperty y;
    private Optional<Node> nodeOptional;

    public Dot(Pane animationPane, double x, double y) {
        this.x = new SimpleDoubleProperty(0);
        this.y = new SimpleDoubleProperty(0);
        this.x.bind(animationPane.widthProperty().multiply(x));
        this.y.bind(animationPane.heightProperty().multiply(y));
        this.nodeOptional = Optional.empty();
    }

    public Dot(DotGroup dotGroup, double x, double y) {
        this.x = new SimpleDoubleProperty(0);
        this.y = new SimpleDoubleProperty(0);
        this.x.bind(dotGroup.xProperty().add(dotGroup.widthProperty().multiply(x)));
        this.y.bind(dotGroup.yProperty().add(dotGroup.heightProperty().multiply(y)));
        this.nodeOptional = Optional.empty();
    }

    public Optional<Node> setNode(Optional<Node> nodeOptional) {
        Optional<Node> obsolete = this.nodeOptional;
        this.nodeOptional = nodeOptional;
        return obsolete;
    }

    public Optional<Node> getNode() {
        return this.nodeOptional;
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

    @Override
    public String toString() {
        return String.format("(%s,%s)", getX(), getY());
    }
}
