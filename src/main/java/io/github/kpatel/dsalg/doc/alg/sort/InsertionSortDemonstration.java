package io.github.kpatel.dsalg.doc.alg.sort;

import io.github.kpatel.dsalg.doc.Demonstration;
import io.github.kpatel.dsalg.model.sort.InsertionSort;
import io.github.kpatel.dsalg.view.controls.BubbleMarker;
import io.github.kpatel.dsalg.view.controls.BubbleRecord;
import io.github.kpatel.dsalg.model.DotGroupFactory;
import io.github.kpatel.dsalg.model.DotPool;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class InsertionSortDemonstration extends Demonstration {
    private final static int DATA_SIZE = 10;
    private final static int MAX_VALUE = 10;
    private static final int MIN_VALUE = 1;
    private final static String[] markerNames = {"Window","Limit"};

    public InsertionSortDemonstration() {
        super("Insertion Sort", "insertion_sort.fxml");
    }

    @Override
    public Animation makeAnimation(Pane animationPane) {
        DotPool dotPool = new DotPool();

        //Model
        int value;
        Random random = new Random();
        ArrayList<Integer> dataModel = new ArrayList<>(DATA_SIZE);
        dotPool.getDotGroups().put("Sequence", DotGroupFactory.makeHorizontalGroup(
                animationPane, 0.1, 0.5, 0.8, 0.0, DATA_SIZE));
        for (int i = 0; i < DATA_SIZE; i++) {
            value = random.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE;
            dataModel.add(i, value);
            BubbleRecord bubbleRecord = new BubbleRecord(value,MAX_VALUE,false);
            dotPool.getDot("Sequence",i).setNode(Optional.of(bubbleRecord));
            animationPane.getChildren().add(bubbleRecord);
        }

        //Markers
        for(String name : markerNames)
            dotPool.getMarkers().put(name,new BubbleMarker());
        animationPane.getChildren().addAll(dotPool.getMarkers().values());

        return dotPool.applyDeltas(new InsertionSort<>(dataModel));
    }
}
