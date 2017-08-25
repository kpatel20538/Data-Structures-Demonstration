package io.github.kpatel.dsalg.doc.alg.search;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.search.BinarySearch;
import io.github.kpatel.dsalg.model.DeltaSuccess;
import io.github.kpatel.dsalg.view.controls.BubbleMarker;
import io.github.kpatel.dsalg.view.controls.BubbleRecord;
import io.github.kpatel.dsalg.view.controls.BubblePost;
import io.github.kpatel.dsalg.model.DotGroupFactory;
import io.github.kpatel.dsalg.model.DotPool;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

public class BinarySearchDemonstration extends Demonstration {
    private final static int DATA_SIZE = 10;
    private final static int MAX_VALUE = 10;
    private final static int MIN_VALUE = 0;

    private final static String[] markerNames = {"High","Middle","Low"};

    public BinarySearchDemonstration() {
        super("Binary Search", "binary_search.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        DotPool dotPool = new DotPool();

        //Model
        Random random = new Random();
        int target = random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE;
        ArrayList<Integer> dataModel = new ArrayList<>(DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++)
            dataModel.add(random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE);
        Collections.sort(dataModel);

        dotPool.getDotGroups().put("Sequence", DotGroupFactory.makeHorizontalGroup(
                animationPane, 0.1, 0.5, 0.8, 0.0, DATA_SIZE));

        int value;
        for (int i = 0; i < DATA_SIZE; i++) {
            value = dataModel.get(i);
            BubbleRecord bubbleRecord = new BubbleRecord(value,MAX_VALUE,false);
            dotPool.getDot("Sequence",i).setNode(Optional.of(bubbleRecord));
            animationPane.getChildren().add(bubbleRecord);
        }
        Collections.sort(dataModel);

        //Goalpost View
        BubblePost goalPost = new BubblePost(target,MAX_VALUE);
        goalPost.translateXProperty().bind(animationPane.widthProperty().multiply(0.1));
        goalPost.translateYProperty().bind(animationPane.heightProperty().multiply(0.1));
        animationPane.getChildren().add(goalPost);

        //Markers
        for(String name : markerNames)
            dotPool.getMarkers().put(name,new BubbleMarker());
        animationPane.getChildren().addAll(dotPool.getMarkers().values());

        dotPool.setPostInterceptor((pool,delta) -> {
            if(delta instanceof DeltaSuccess){
                boolean success = ((DeltaSuccess) delta).isSuccess();
                return goalPost.setSignal(success ? BubblePost.Signal.SUCCESS: BubblePost.Signal.FAILURE);
            }else{
                return new PauseTransition(Duration.ZERO);
            }
        });

        return dotPool.applyDeltas(new BinarySearch<>(dataModel, target));
    }
}
