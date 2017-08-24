package io.github.kpatel.dsalg.doc.alg.search;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.util.DeltaSuccess;
import io.github.kpatel.dsalg.model.search.LinearSearch;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.view.ArrowMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.SignPost;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroup;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroupFactory;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.*;

public class LinearSearchDemonstration extends Demonstration {
    private final static int DATA_SIZE = 50;
    private final static int MAX_VALUE = 50;
    private final static int MIN_VALUE = 1;

    private final static String[] markerNames = {"Needle"};

    public LinearSearchDemonstration() {
        super("Linear Search", "linear_search.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        //Model
        Random random = new Random();
        ArrayList<Integer> dataModel = new ArrayList<>(DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++)
            dataModel.add(i, random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE);
        int target = random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE;

        //Markers
        HashMap<String, Node> markers = new HashMap<>();
        for(String name : markerNames)
            markers.put(name,new ArrowMarker());
        animationPane.getChildren().addAll(markers.values());

        //Goalpost View
        SignPost goalPost = new SignPost(new BubbleNode(target,true));
        goalPost.translateXProperty().bind(animationPane.widthProperty().multiply(0.1));
        goalPost.translateYProperty().bind(animationPane.heightProperty().multiply(0.1));
        animationPane.getChildren().add(goalPost);

        //Positioning
        DotGroup dotGroup = DotGroupFactory.makeHorizontalGroup(
                animationPane, 0.1, 0.5, 0.8, 0.0, DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++) {
            BubbleNode bubbleNode = new BubbleNode(dataModel.get(i),false);
            dotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }

        //Animation by Deltas
        SequentialTransition sequentialTransition = new SequentialTransition(dotGroup.fadeIn());
        Optional<Transition> transition;
        for (Delta delta : new LinearSearch<>(dataModel, target)) {
            transition = Optional.empty();
            if(delta instanceof DeltaMoveMarker){
                DeltaMoveMarker moveMarker = (DeltaMoveMarker) delta;
                transition = Optional.of( new SequentialTransition(
                        dotGroup.moveMarker(markers.get(
                                moveMarker.getName()),moveMarker.getTarget(),15,-20),
                        dotGroup.getDots().get(moveMarker.getTarget()).getNode()
                                .map(node -> ((BubbleNode) node).flipUp())
                                .orElse(new PauseTransition())
                ));
            } else if(delta instanceof DeltaSuccess){
                boolean success = ((DeltaSuccess) delta).isSuccess();
                markCuePoint(sequentialTransition,success? "Target Found":"Target Not Found");
                transition = Optional.of(goalPost.signal(success));
            }
            transition.ifPresent(sequentialTransition.getChildren()::add);
        }
        return sequentialTransition;
    }
}