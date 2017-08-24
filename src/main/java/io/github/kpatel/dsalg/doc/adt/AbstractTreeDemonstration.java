package io.github.kpatel.dsalg.doc.adt;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.view.ArrowMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroup;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroupFactory;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class AbstractTreeDemonstration extends Demonstration {
    private final static int DATA_SIZE = 10;
    private final static int MAX_VALUE = 10;
    private final static int MIN_VALUE = 1;

    private final static String[] markerNames = {"High","Middle","Low"};

    public AbstractTreeDemonstration() {
        super("Tree (Abstract)", "tree_abstract.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        //Model
        Random random = new Random();
        ArrayList<Integer> dataModel = new ArrayList<>(DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++)
            dataModel.add(i, random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE);

        //Markers
        HashMap<String, Node> markers = new HashMap<>();
        for(String name : markerNames)
            markers.put(name,new ArrowMarker());
        animationPane.getChildren().addAll(markers.values());

        //Positioning
        DotGroup dotGroup = DotGroupFactory.makeTreeGroup(
                animationPane, 0.1, 0.1, 0.8, 0.8, 4);
        for (int i = 0; i < DATA_SIZE; i++) {
            BubbleNode bubbleNode = new BubbleNode(dataModel.get(i),true);
            dotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }

        //Animation by Deltas
        SequentialTransition sequentialTransition = new SequentialTransition(dotGroup.fadeIn());
        /*
        Optional<Transition> transition;
        for (Delta delta : new BubbleSort<>(dataModel)) {
            transition = Optional.empty();
            if (delta instanceof DeltaSwap) {
                DeltaSwap swap = (DeltaSwap) delta;
                transition = Optional.of(linearDotGroup.swapDots(
                        swap.getLeft(), swap.getRight()));
            }else if(delta instanceof DeltaMoveMarker){
                DeltaMoveMarker moveMarker = (DeltaMoveMarker) delta;
                transition = Optional.of(linearDotGroup.moveMarker(
                        markers.get(moveMarker.getName()),moveMarker.getTarget(),15,-20));
            }
            transition.ifPresent(sequentialTransition.getChildren()::add);
        }*/
        return sequentialTransition;
    }
}
