package io.github.kpatel.dsalg.view.controls;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BubbleRecord extends StackPane {
    private Circle circle;
    private Text text;
    private Color fill;

    private boolean flippedUp;

    public BubbleRecord(int value, int total, boolean flippedUp) {
        circle = new Circle(20);
        text = new Text(Integer.toString(value));
        fill = Color.hsb(360.0 * (value%total) / total, 0.8, 0.8);
        circle.setFill(flippedUp ? fill:Color.BLACK);
        text.setOpacity(flippedUp ? 1.0:0.0);
        circle.setStroke(Color.BLACK);
        this.flippedUp = flippedUp;
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

    public boolean isFlippedUp(){
        return this.flippedUp;
    }

    public Transition setFlip(boolean flippedUp){
        if(isFlippedUp() == flippedUp)
            return new PauseTransition(Duration.ZERO);

        FillTransition ct = new FillTransition(Duration.seconds(1),getCircle());
        ct.setToValue(flippedUp ? getFill():Color.BLACK);

        FadeTransition tt = new FadeTransition(Duration.seconds(1),getText());
        tt.setToValue(flippedUp ? 1.0:0.0);

        this.flippedUp = flippedUp;
        return new ParallelTransition(ct,tt);
    }

}
