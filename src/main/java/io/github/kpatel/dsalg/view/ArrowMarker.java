package io.github.kpatel.dsalg.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class ArrowMarker extends StackPane {
    public ArrowMarker(String name){
        Circle circle = new Circle(20);
        Polygon triangle = new Polygon();
        Text text = new Text(name);
        //circle.setFill(Color.hsb(360.0*i/10.0,0.8,0.8));
        this.getChildren().addAll(circle,text);
    }
}
