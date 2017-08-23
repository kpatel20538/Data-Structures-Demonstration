package io.github.kpatel.dsalg.model;

import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.model.util.Generator;

import java.util.Collections;
import java.util.List;

public class SelectionSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public SelectionSort(List<E> list){
        this.list = list;
    }

    @Override
    protected void apply() {
        int minIndex;
        for (int i = 1; i < list.size(); i++) {
            push(new DeltaMoveMarker("Minimum",i-1));
            minIndex = i-1;
            for (int j = i; j < list.size(); j++){
                if (list.get(j).compareTo(list.get(minIndex)) == -1){
                    push(new DeltaMoveMarker("Minimum",j));
                    minIndex = j;
                }
            }
            if (minIndex != i-1) {
                Collections.swap(list,i-1,minIndex);
                push(new DeltaMoveMarker("Position",i));
                push(new DeltaSwap(i-1,minIndex));
            }
        }
    }
}
