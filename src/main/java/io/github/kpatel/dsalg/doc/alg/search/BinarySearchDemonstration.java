package io.github.kpatel.dsalg.doc.alg.search;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.search.BinarySearch;
import io.github.kpatel.dsalg.model.search.LinearSearch;
import io.github.kpatel.dsalg.model.util.DeltaMarker;
import io.github.kpatel.dsalg.model.util.DeltaSuccess;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.view.BubbleMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.SignPost;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroup;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroupFactory;
import io.github.kpatel.dsalg.view.video.animate.prims.DotPool;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

public class BinarySearchDemonstration extends Demonstration {
    private final static int DATA_SIZE = 10;
    private final static int MAX_VALUE = 10;
    private final static int MIN_VALUE = 1;

    private final static String[] markerNames = {"High","Middle","Low"};

    public BinarySearchDemonstration() {
        super("Binary Search", "binary_search.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        //Model
        Random random = new Random();
        ArrayList<Integer> dataModel = new ArrayList<>(DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++)
            dataModel.add(i, random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE);
        Collections.sort(dataModel);
        int target = random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE;

        DotPool dotPool = new DotPool();

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
        dotPool.getDotGroups().put("Sequence",dotGroup);

        //Markers
        for(String name : markerNames)
            dotPool.getMarkers().put(name,new BubbleMarker());
        animationPane.getChildren().addAll(dotPool.getMarkers().values());

        dotPool.setPostInterceptor((pool,delta) -> {
            if(delta instanceof DeltaSuccess){
                boolean success = ((DeltaSuccess) delta).isSuccess();
                return goalPost.signal(success);
            }else{
                return new PauseTransition(Duration.ZERO);
            }
        });

        return dotPool.applyDeltas(new BinarySearch<>(dataModel, target));
    }
}
