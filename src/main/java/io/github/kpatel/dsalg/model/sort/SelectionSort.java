package io.github.kpatel.dsalg.model.sort;

import io.github.kpatel.dsalg.model.*;

import java.util.Collections;
import java.util.List;

public class SelectionSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public SelectionSort(List<E> list) {
        this.list = list;
    }

    @Override
    protected void apply() {
        push(new DeltaStart());
        int minIndex;
        for (int i = 1; i < list.size(); i++) {
            push(new DeltaMarker("Sequence", i-1,"Position"));
            push(new DeltaFlip("Sequence", i-1,true));
            minIndex = i - 1;
            for (int j = i; j < list.size(); j++) {
                push(new DeltaMarker("Sequence", j,"Minimum"));
                push(new DeltaFlip("Sequence", j,true));
                if (list.get(j).compareTo(list.get(minIndex)) == -1) {
                    minIndex = j;
                }
            }
            if (minIndex != i - 1) {
                Collections.swap(list, i - 1, minIndex);
                push(new DeltaSwap("Sequence",i - 1, minIndex));
            }
        }
        push(new DeltaSuccess(true));

    }
}
