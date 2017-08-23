package io.github.kpatel.dsalg.doc.alg.search;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.BubbleSort;
import io.github.kpatel.dsalg.model.search.DeltaSuccess;
import io.github.kpatel.dsalg.model.search.LinearSearch;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.model.util.RandomUtil;
import io.github.kpatel.dsalg.view.ArrowMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.video.animate.prims.LinearDotGroup;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class LinearSearchDemonstration extends Demonstration {
    public LinearSearchDemonstration() {
        super("Linear Search", "linear_search.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        ArrayList<Integer> arrayList = RandomUtil.nextList(10);
        Integer target = new Random().nextInt(10)+1;
        LinearDotGroup linearDotGroup = new LinearDotGroup(animationPane, 0.1, 0.5, 0.8, 0.0, arrayList.size());
        ArrowMarker windowMarker = new ArrowMarker();
        animationPane.getChildren().add(windowMarker);
        for (int i = 0; i < arrayList.size(); i++) {
            BubbleNode bubbleNode = new BubbleNode(arrayList.get(i),false);
            linearDotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().add(linearDotGroup.fadeIn());
        for (Delta delta : new LinearSearch<>(arrayList,target)) {
            if(delta instanceof DeltaMoveMarker){
                DeltaMoveMarker moveMarker = (DeltaMoveMarker) delta;
                Transition transition = linearDotGroup.moveMarker(windowMarker,moveMarker.getTarget(),15,-20);
                Transition flipUp = linearDotGroup.getDots().get(moveMarker.getTarget()).getNode().map(node -> ((BubbleNode) node).flipUp()).orElse(new PauseTransition(Duration.seconds(1)));
                sequentialTransition.getChildren().addAll(transition,flipUp);
            }else if(delta instanceof DeltaSuccess){
                System.out.println(((DeltaSuccess) delta).isSuccess());
                System.out.println(target);
            }
        }
        return sequentialTransition;
    }
}