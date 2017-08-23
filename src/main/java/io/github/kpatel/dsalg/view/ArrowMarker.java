package io.github.kpatel.dsalg.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class ArrowMarker extends StackPane {
    public ArrowMarker() {
        Polygon triangle = new Polygon(0, 0, 5, 10, 10, 0);
        triangle.setFill(Color.BLACK);
        this.getChildren().addAll(triangle);
    }
}
