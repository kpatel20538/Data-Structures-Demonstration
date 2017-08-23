package io.github.kpatel.dsalg.model.util;

public class DeltaMoveMarker extends Delta {
    private final String name;
    private final int target;

    public DeltaMoveMarker(String name, int target) {
        this.name = name;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public int getTarget() {
        return target;
    }
}
