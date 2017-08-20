package io.github.kpatel.dsalg.doc;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TestAnimation extends Demonstration{
    public TestAnimation() {
        super("doc/test_animation.fxml", "Test");
    }

    @Override
    public Timeline makeAnimation(Pane animationPane) {
        Circle circle = new Circle(50);
        animationPane.getChildren().add(circle);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5),circle);
        translateTransition.fromXProperty().bind(animationPane.widthProperty().multiply(0.1));
        translateTransition.fromYProperty().bind(animationPane.heightProperty().multiply(0.5));
        translateTransition.toXProperty().bind(animationPane.widthProperty().multiply(0.9));
        translateTransition.toYProperty().bind(animationPane.heightProperty().multiply(0.1));
        return new Timeline(60,
                new KeyFrame(Duration.ZERO,"Test 1",
                        actionEvent -> translateTransition.play()
                ),
                new KeyFrame(Duration.seconds(5),"Test 2"

                )
        );
    }
}
