package io.github.kpatel.dsalg.view;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BubbleNode extends StackPane {
    private Circle circle;
    private Text text;
    private Color fill;
    public BubbleNode(int i,boolean flippedUp) {
        circle = new Circle(20);
        text = new Text(Integer.toString(i));
        fill = Color.hsb(360.0 * i / 10.0, 0.8, 0.8);
        circle.setFill(flippedUp ? fill:Color.BLACK);
        text.setOpacity(flippedUp ? 1.0:0.0);
        this.getChildren().addAll(circle, text);
    }
    public Circle getCircle(){
        return circle;
    }

    public Text getText() {
        return text;
    }

    public Color getFill(){
        return fill;
    }

    public Transition flipDown(){
        FillTransition ct = new FillTransition(Duration.seconds(1),getCircle());
        FadeTransition tt = new FadeTransition(Duration.seconds(1),getText());
        ct.setToValue(Color.BLACK);
        tt.setToValue(0.0);
        return new ParallelTransition(ct,tt);
    }
    public Transition flipUp(){
        FillTransition ct = new FillTransition(Duration.seconds(1),getCircle());
        FadeTransition tt = new FadeTransition(Duration.seconds(1),getText());
        ct.setToValue(getFill());
        tt.setToValue(1.0);
        return new ParallelTransition(ct,tt);
    }

}
