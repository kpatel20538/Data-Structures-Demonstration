package io.github.kpatel.dsalg.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class Generator<E> implements Iterable<E> {
    private final ArrayList<E> elements = new ArrayList<>();

    protected abstract void apply();

    public void push(E element) {
        getElements().add(element);
    }

    public void pushAll(Collection<? extends E> elements) {
        getElements().addAll(elements);
    }

    public void pushAll(E... elements) {
        Collections.addAll(getElements(), elements);
    }

    public ArrayList<E> getElements() {
        return elements;
    }

    @Override
    public Iterator<E> iterator() {
        apply();
        return getElements().iterator();
    }
}
