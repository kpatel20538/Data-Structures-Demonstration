package io.github.kpatel.dsalg.doc.alg.sort;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.sort.SelectionSort;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.view.BubbleMarker;
import io.github.kpatel.dsalg.view.BubbleNode;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroup;
import io.github.kpatel.dsalg.view.video.animate.prims.DotGroupFactory;
import io.github.kpatel.dsalg.view.video.animate.prims.DotPool;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class SelectionSortDemonstration extends Demonstration {
    private final static int DATA_SIZE = 10;
    private final static int MAX_VALUE = 10;
    private static final int MIN_VALUE = 1;
    private final static String[] markerNames = {"Position","Minimum"};

    public SelectionSortDemonstration() {
        super("Selection Sort", "selection_sort.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        //Model
        Random random = new Random();
        ArrayList<Integer> dataModel = new ArrayList<>(DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++)
            dataModel.add(i, random.nextInt(MAX_VALUE-MIN_VALUE)+MIN_VALUE);

        DotPool dotPool = new DotPool();

        //Positioning
        DotGroup dotGroup = DotGroupFactory.makeHorizontalGroup(
                animationPane, 0.1, 0.5, 0.8, 0.0, DATA_SIZE);
        for (int i = 0; i < DATA_SIZE; i++) {
            BubbleNode bubbleNode = new BubbleNode(dataModel.get(i),true);
            dotGroup.getDots().get(i).setNode(Optional.of(bubbleNode));
            animationPane.getChildren().add(bubbleNode);
        }
        dotPool.getDotGroups().put("Sequence",dotGroup);

        //Markers
        for(String name : markerNames)
            dotPool.getMarkers().put(name,new BubbleMarker());
        animationPane.getChildren().addAll(dotPool.getMarkers().values());

        return dotPool.applyDeltas(new SelectionSort<>(dataModel));
    }
}
