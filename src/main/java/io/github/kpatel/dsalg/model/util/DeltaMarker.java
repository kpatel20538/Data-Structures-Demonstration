package io.github.kpatel.dsalg.model.util;

public class DeltaMarker extends Delta {
    private final String group;
    private final int idx;
    private final String marker;

    public DeltaMarker(String group, int idx, String marker) {
        this.group = group;
        this.idx = idx;
        this.marker = marker;
    }

    public String getGroup() {
        return group;
    }

    public int getIdx() {
        return idx;
    }

    public String getMarker() {
        return marker;
    }

}
