package io.github.kpatel.dsalg.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class BubbleNode extends StackPane {
    public BubbleNode(int i){
        Circle circle = new Circle(20);
        Text text = new Text(Integer.toString(i));
        circle.setFill(Color.hsb(360.0*i/10.0,0.8,0.8));
        this.getChildren().addAll(circle,text);
    }
}
