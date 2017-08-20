package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.video.TranslateBuilder;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TestAnimation extends Demonstration{
    public TestAnimation() {
        super("doc/test_animation.fxml", "Test");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        Circle circle = new Circle(50);
        animationPane.getChildren().add(circle);
        return new TranslateBuilder(animationPane)
                .setNode(circle)
                .setDuration(Duration.seconds(5))
                .moveFrom(0.1,0.5)
                .moveTo(0.9,0.1)
                .build();
    }
}
