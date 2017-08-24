package io.github.kpatel.dsalg.model.search;

import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSuccess;
import io.github.kpatel.dsalg.model.util.Generator;

import java.util.List;

public class LinearSearch<E> extends Generator<Delta> {
    private List<E> list;
    private E target;
    public LinearSearch(List<E> list, E target) {
        this.list = list;
        this.target = target;
    }

    @Override
    protected void apply() {
        for(int i = 0; i < list.size(); i++){
            push(new DeltaMoveMarker("Needle",i));
            if(list.get(i).equals(target)){
                push(new DeltaSuccess(true));
                return;
            }
        }
        push(new DeltaSuccess(false));
    }
}
