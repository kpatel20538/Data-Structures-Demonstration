package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.view.video.animate.builder.TranslateBuilder;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class IntroductionDemonstration extends Demonstration {
    public IntroductionDemonstration() {
        super("Data Structures and AlgorithmsDemonstration", "introduction.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new AbstractDataTypeDemonstration().getItem(),
                new AlgorithmsDemonstration().getItem()
        );
        root.setExpanded(true);
        return root;
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        Text topText = new Text("Data Structure &");
        Text bottomText = new Text("Algorithms");
        topText.setFont(Font.font(36));
        bottomText.setFont(Font.font(36));
        animationPane.getChildren().addAll(topText, bottomText);

        return new ParallelTransition(
                new TranslateBuilder(animationPane)
                        .setNode(topText)
                        .setDuration(Duration.seconds(6))
                        .from(TranslateBuilder.Offscreen.LEFT, 0.25)
                        .to(TranslateBuilder.Offscreen.RIGHT, 0.25)
                        .build(),
                new TranslateBuilder(animationPane)
                        .setNode(bottomText)
                        .setDelay(Duration.seconds(2))
                        .setDuration(Duration.seconds(6))
                        .from(TranslateBuilder.Offscreen.RIGHT, 0.75)
                        .to(TranslateBuilder.Offscreen.LEFT, 0.75)
                        .build()
        );
    }
}
