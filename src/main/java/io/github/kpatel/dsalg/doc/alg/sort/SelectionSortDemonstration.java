package io.github.kpatel.dsalg.doc.alg.sort;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.sort.SelectionSort;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.model.util.RandomUtil;
import io.github.kpatel.dsalg.view.ArrowMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.video.animate.prims.LinearDotGroup;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class SelectionSortDemonstration extends Demonstration {
    public SelectionSortDemonstration() {
        super("Selection Sort", "selection_sort.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        ArrayList<Integer> arrayList = RandomUtil.nextList(10);
        LinearDotGroup linearDotGroup = new LinearDotGroup(animationPane, 0.1, 0.5, 0.8, 0.0, arrayList.size());
        HashMap<String,ArrowMarker> markers = new HashMap<>();
        markers.put("Position",new ArrowMarker());
        markers.put("Minimum",new ArrowMarker());
        animationPane.getChildren().addAll(markers.values());
        for (int i = 0; i < arrayList.size(); i++) {
            BubbleNode bubbleNode = new BubbleNode(arrayList.get(i),true);
            linearDotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().add(linearDotGroup.fadeIn());
        for (Delta delta : new SelectionSort<>(arrayList)) {
            if (delta instanceof DeltaSwap) {
                DeltaSwap swap = (DeltaSwap) delta;
                Transition transition = linearDotGroup.swapDots(swap.getLeft(), swap.getRight());
                sequentialTransition.getChildren().add(transition);
            }else if(delta instanceof DeltaMoveMarker){
                DeltaMoveMarker moveMarker = (DeltaMoveMarker) delta;
                Transition transition = linearDotGroup.moveMarker(markers.get(moveMarker.getName()),moveMarker.getTarget(),15,-20);
                sequentialTransition.getChildren().add(transition);
            }
        }
        return sequentialTransition;
    }
}
