package io.github.kpatel.dsalg.model.sort;

import io.github.kpatel.dsalg.model.util.*;

import java.util.Collections;
import java.util.List;

public class InsertionSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public InsertionSort(List<E> list) {
        this.list = list;
    }

    @Override
    protected void apply() {
        push(new DeltaStart());
        for (int i = 1; i < list.size(); i++) {
            push(new DeltaMarker("Sequence", i,"Limit"));
            push(new DeltaMarker("Sequence", i-1,"Window"));
            for (int j = i; j > 0 && list.get(j - 1).compareTo(list.get(j)) == 1; j--) {
                Collections.swap(list, j - 1, j);
                push(new DeltaMarker("Sequence", j-1,"Window"));
                push(new DeltaSwap("Sequence",j - 1, j));
            }
        }
        push(new DeltaSuccess(true));

    }
}
