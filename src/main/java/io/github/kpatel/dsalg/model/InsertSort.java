package io.github.kpatel.dsalg.model;

import io.github.kpatel.dsalg.model.util.Delta;
import io.github.kpatel.dsalg.model.util.DeltaMoveMarker;
import io.github.kpatel.dsalg.model.util.DeltaSwap;
import io.github.kpatel.dsalg.model.util.Generator;

import java.util.Collections;
import java.util.List;

public class InsertSort<E extends Comparable<E>> extends Generator<Delta> {
    private List<E> list;

    public InsertSort(List<E> list){
        this.list = list;
    }

    @Override
    protected void apply() {
        for(int i=1;i<list.size();i++){
            for(int j = i;j > 0 && list.get(j-1).compareTo(list.get(j))==1;j--){
                Collections.swap(list,j-1,j);
                push(new DeltaMoveMarker("Window",j));
                push(new DeltaSwap(j-1,j));
            }
        }
    }
}
