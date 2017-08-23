package io.github.kpatel.dsalg.doc.alg.sort;

import io.github.kpatel.dsalg.model.BubbleSort;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.util.RandomUtil;
import io.github.kpatel.dsalg.view.video.animate.prims.LinearDotGroup;
import io.github.kpatel.dsalg.view.BubbleNode;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;

public class BubbleSortDemonstration extends Demonstration {
    public BubbleSortDemonstration() {
        super("Bubble Sort","bubble_sort.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        ArrayList<Integer> arrayList = RandomUtil.nextList(10);
        LinearDotGroup linearDotGroup = new LinearDotGroup(animationPane,0.1,0.5,0.8,0.0,arrayList.size());
        for(int i = 0;i<arrayList.size();i++){
            BubbleNode bubbleNode = new BubbleNode(arrayList.get(i));
            linearDotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().add(linearDotGroup.fadeIn());
        for (Delta delta : new BubbleSort<>(arrayList)){
            if(delta instanceof DeltaSwap){
                DeltaSwap swap = (DeltaSwap) delta;
                Transition transition = linearDotGroup.swapDots(swap.getLeft(),swap.getRight());
                transition.setDelay(Duration.seconds(1));
                sequentialTransition.getChildren().add(transition);
            }
        }
        return sequentialTransition;
    }


}
