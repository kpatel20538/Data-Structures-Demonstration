package io.github.kpatel.dsalg.model.search;

import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.Generator;

import java.util.List;

public class BinarySearch<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;
    private E target;
    public BinarySearch(List<E> list, E target) {
        this.list = list;
        this.target = target;
    }

    @Override
    protected void apply() {
        int low = 0;
        int high = list.size() - 1;
        push(new DeltaMoveMarker("Low",low));
        push(new DeltaMoveMarker("High",high));

        while (low <= high) {
            int middle = low + (high - low) / 2;
            push(new DeltaMoveMarker("Middle",middle));
            int comparison = this.target.compareTo(list.get(middle));
            if(comparison == -1){
                high = middle - 1;
                if(low <= high)
                    push(new DeltaMoveMarker("High", high));
            }else if(comparison == 1){
                low = middle + 1;
                if(low <= high)
                    push(new DeltaMoveMarker("Low", low));
            }else{
                push(new DeltaSuccess(true));
                return;
            }
        }
        push(new DeltaSuccess(false));

    }
}
