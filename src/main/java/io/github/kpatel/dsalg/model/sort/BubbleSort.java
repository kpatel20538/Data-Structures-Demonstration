package io.github.kpatel.dsalg.model.sort;

import io.github.kpatel.dsalg.model.util.*;
import io.github.kpatel.dsalg.model.util.DeltaMarker;

import java.util.Collections;
import java.util.List;

public class BubbleSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public BubbleSort(List<E> list) {
        this.list = list;
    }

    @Override
    protected void apply() {
        push(new DeltaStart());
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < list.size(); i++) {
                push(new DeltaMarker("Sequence", i-1,"Window"));
                if (list.get(i - 1).compareTo(list.get(i)) == 1) {
                    Collections.swap(list, i - 1, i);
                    push(new DeltaSwap("Sequence",i - 1, i));
                    swapped = true;
                }
            }
        } while (swapped);
        push(new DeltaSuccess(true));
    }
}
