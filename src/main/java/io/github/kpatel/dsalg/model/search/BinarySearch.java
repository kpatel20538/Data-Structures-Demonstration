package io.github.kpatel.dsalg.model.search;

import io.github.kpatel.dsalg.model.*;

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
        push(new DeltaStart());
        int low = 0;
        int high = list.size() - 1;
        push(new DeltaMarker("Sequence",low,"Low"));
        push(new DeltaMarker("Sequence",high,"High"));

        while (low <= high) {
            int middle = low + (high - low) / 2;
            push(new DeltaMarker("Sequence",middle,"Middle"));
            push(new DeltaFlip("Sequence",middle,true));
            int comparison = this.target.compareTo(list.get(middle));
            if(comparison == -1){
                high = middle - 1;
                if(low <= high)
                    push(new DeltaMarker("Sequence", high,"High"));
            }else if(comparison == 1){
                low = middle + 1;
                if(low <= high)
                    push(new DeltaMarker("Sequence", low,"Low"));
            }else{
                push(new DeltaSuccess(true));
                return;
            }
        }
        push(new DeltaSuccess(false));

    }
}
