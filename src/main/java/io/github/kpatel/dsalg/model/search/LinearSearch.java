package io.github.kpatel.dsalg.model.search;

import io.github.kpatel.dsalg.model.util.*;

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
        push(new DeltaStart());
        for(int i = 0; i < list.size(); i++){
            push(new DeltaMarker("Sequence",i,"Needle"));
            push(new DeltaFlip("Sequence",i,true));
            if(list.get(i).equals(target)){
                push(new DeltaSuccess(true));
                return;
            }
        }
        push(new DeltaSuccess(false));
    }
}
