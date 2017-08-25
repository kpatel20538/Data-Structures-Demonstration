package io.github.kpatel.dsalg.doc;

import io.github.kpatel.dsalg.model.Delta;
import io.github.kpatel.dsalg.model.DeltaSwap;
import io.github.kpatel.dsalg.model.DeltaWait;
import io.github.kpatel.dsalg.model.Dot;
import io.github.kpatel.dsalg.model.DotGroup;
import io.github.kpatel.dsalg.model.DotPool;
import javafx.animation.Animation;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;

public class IntroductionDemonstration extends Demonstration {
    public IntroductionDemonstration() {
        super("Data Structures and Algorithms", "introduction.fxml");
    }

    @Override
    public TreeItem<Demonstration> getItem() {
        TreeItem<Demonstration> root = super.getItem();
        root.getChildren().addAll(
                new AbstractDataTypeDemonstration().getItem(),
                new ConcreteDataStructureDemonstration().getItem(),
                new ImplementationDemonstration().getItem(),
                new AlgorithmsDemonstration().getItem()
        );
        root.setExpanded(true);
        return root;
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        Text topText = new Text("Algorithms");
        Text andText = new Text("&");
        Text bottomText = new Text("Data Structures");
        topText.setFont(Font.font(36));
        andText.setFont(Font.font(36));
        bottomText.setFont(Font.font(36));
        animationPane.getChildren().addAll(topText, andText, bottomText);

        DotPool dotPool = new DotPool();
        dotPool.getDotGroups().put("KeyPoints",new DotGroup(animationPane,0.1,0.1,0.8,0.8));
        dotPool.addDot("KeyPoints",0.0,0.25).setNode(topText);
        dotPool.addDot("KeyPoints",0.0,0.75).setNode(andText);
        dotPool.addDot("KeyPoints",0.1,0.75).setNode(bottomText);

        ArrayList<Delta> deltas = new ArrayList<>();
        for(int i = 0; i < 3; i++)
            deltas.add(new DeltaSwap("KeyPoints",0,2));
        deltas.add(new DeltaWait(Duration.seconds(4)));
        return dotPool.applyDeltas(deltas);
    }
}
