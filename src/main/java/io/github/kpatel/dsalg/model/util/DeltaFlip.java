package io.github.kpatel.dsalg.model.util;

public class DeltaFlip extends Delta {
    private final String group;
    private final int idx;
    private final boolean flippedUp;

    public DeltaFlip(String group, int idx, boolean flippedUp) {
        this.group = group;
        this.idx = idx;
        this.flippedUp = flippedUp;
    }

    public String getGroup() {
        return group;
    }

    public int getIdx() {
        return idx;
    }

    public boolean isFlippedUp() {
        return flippedUp;
    }
}
