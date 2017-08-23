package io.github.kpatel.dsalg.model;

import io.github.kpatel.dsalg.model.search.DeltaSuccess;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.model.util.Generator;

import java.util.Collections;
import java.util.List;

public class BubbleSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public BubbleSort(List<E> list) {
        this.list = list;
    }

    @Override
    protected void apply() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < list.size(); i++) {
                push(new DeltaMoveMarker("Window", i-1));
                if (list.get(i - 1).compareTo(list.get(i)) == 1) {
                    Collections.swap(list, i - 1, i);
                    push(new DeltaSwap(i - 1, i));
                    swapped = true;
                }
            }
        } while (swapped);
        push(new DeltaSuccess(true));
    }
}
