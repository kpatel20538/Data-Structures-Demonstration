package io.github.kpatel.dsalg.doc.alg.search;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.search.BinarySearch;
import io.github.kpatel.dsalg.model.search.DeltaSuccess;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.RandomUtil;
import io.github.kpatel.dsalg.view.ArrowMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.video.animate.prims.LinearDotGroup;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

public class BinarySearchDemonstration extends Demonstration {
    public BinarySearchDemonstration() {
        super("Binary Search", "binary_search.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        ArrayList<Integer> arrayList = RandomUtil.nextList(10);
        Collections.sort(arrayList);
        Integer target = new Random().nextInt(10)+1;
        LinearDotGroup linearDotGroup = new LinearDotGroup(animationPane, 0.1, 0.5, 0.8, 0.0, arrayList.size());
        HashMap<String,ArrowMarker> markers = new HashMap<>();
        markers.put("High",new ArrowMarker());
        markers.put("Middle",new ArrowMarker());
        markers.put("Low",new ArrowMarker());
        animationPane.getChildren().addAll(markers.values());
        for (int i = 0; i < arrayList.size(); i++) {
            BubbleNode bubbleNode = new BubbleNode(arrayList.get(i),false);
            linearDotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().add(linearDotGroup.fadeIn());
        for (Delta delta : new BinarySearch<>(arrayList, target)) {
            if(delta instanceof DeltaMoveMarker){
                DeltaMoveMarker moveMarker = (DeltaMoveMarker) delta;
                Transition transition = linearDotGroup.moveMarker(markers.get(moveMarker.getName()),moveMarker.getTarget(),15,-20);
                sequentialTransition.getChildren().add(transition);
                if(moveMarker.getName().equals("Middle")){
                    sequentialTransition.getChildren().add(linearDotGroup.getDots().get(moveMarker.getTarget()).getNode().map(node -> ((BubbleNode) node).flipUp()).orElse(new PauseTransition(Duration.seconds(1))));
                }
            }else if(delta instanceof DeltaSuccess){
                System.out.println(((DeltaSuccess) delta).isSuccess());
                System.out.println(target);
            }
        }
        return sequentialTransition;
    }
}
