package io.github.kpatel.dsalg.view;

import javafx.animation.FillTransition;
import javafx.animation.Transition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SignPost extends StackPane{
    private Rectangle rect;

    public SignPost(BubbleNode bubbleNode) {
        this.rect = new Rectangle(60,60);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(Color.GOLD);
        this.getChildren().addAll(rect, bubbleNode);
    }
    public Rectangle getRectangle(){
        return rect;
    }

    public Transition signalSuccess(){
        FillTransition ct = new FillTransition(Duration.seconds(1),getRectangle());
        ct.setToValue(Color.LIMEGREEN);
        return ct;
    }
    public Transition signalFailure(){
        FillTransition ct = new FillTransition(Duration.seconds(1),getRectangle());
        ct.setToValue(Color.TOMATO);
        return ct;
    }

    public Transition signal(boolean success){
        return success ? signalSuccess():signalFailure();
    }

}
