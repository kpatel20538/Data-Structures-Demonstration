package io.github.kpatel.dsalg.model.sort;

import io.github.kpatel.dsalg.model.search.DeltaSuccess;
import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.model.util.Generator;

import java.util.Collections;
import java.util.List;

public class InsertionSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public InsertionSort(List<E> list) {
        this.list = list;
    }

    @Override
    protected void apply() {
        for (int i = 1; i < list.size(); i++) {
            push(new DeltaMoveMarker("Limit", i));
            push(new DeltaMoveMarker("Window", i-1));
            for (int j = i; j > 0 && list.get(j - 1).compareTo(list.get(j)) == 1; j--) {
                Collections.swap(list, j - 1, j);
                push(new DeltaMoveMarker("Window", j-1));
                push(new DeltaSwap(j - 1, j));
            }
        }
        push(new DeltaSuccess(true));

    }
}
