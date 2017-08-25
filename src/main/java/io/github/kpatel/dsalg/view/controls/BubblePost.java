package io.github.kpatel.dsalg.view.controls;

import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashMap;


public class BubblePost extends BubbleRecord{
    public enum Signal{
        SUCCESS(Color.LIMEGREEN),
        ONGOING(Color.GOLD),
        FAILURE(Color.TOMATO);

        private final Color color;

        Signal(Color color) {
            this.color = color;
        }

        public Color getColor(){
            return color;
        }
    }

    private Rectangle rect;
    private Signal signal;

    public BubblePost(int value, int total) {
        this(value,total,Signal.ONGOING);
    }

    public BubblePost(int value, int total,Signal signal) {
        super(value,total,true);
        this.rect = new Rectangle(60,60);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(signal.getColor());
        this.signal = signal;
        this.getChildren().add(0,rect);
    }

    public Rectangle getRectangle(){
        return rect;
    }


    public Signal getSignal(){return this.signal;}

    public Transition setSignal(Signal signal){
        if(getSignal() == signal)
            return new PauseTransition(Duration.ZERO);

        FillTransition ct = new FillTransition(Duration.seconds(1),getRectangle());
        ct.setToValue(signal.getColor());

        this.signal = signal;
        return ct;
    }

}
