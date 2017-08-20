package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.video.builder.TranslateBuilder;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TestAnimation2 extends Demonstration {
    public TestAnimation2() {
        super("doc/test_animation2.fxml", "Test2");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        Circle circle = new Circle(25);
        animationPane.getChildren().add(circle);
        return new TranslateBuilder(animationPane)
                .setNode(circle)
                .setDuration(Duration.seconds(5))
                .moveFrom(0.25,0.75)
                .moveTo(0.8,0.5)
                .build();
    }
}
