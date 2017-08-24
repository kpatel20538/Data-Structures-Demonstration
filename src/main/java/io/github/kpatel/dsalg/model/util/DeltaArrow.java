package io.github.kpatel.dsalg.model.util;

public class DeltaArrow extends Delta {
    private final String srcGroup;
    private final String targetGroup;
    private final int srcIdx;
    private final int targetIdx;
    private final boolean visible;

    public DeltaArrow(String group, int srcIdx, int targetIdx,boolean visible) {
        this(group,group,srcIdx,targetIdx,visible);
    }

    public DeltaArrow(String srcGroup, String targetGroup, int srcIdx, int targetIdx,boolean visible) {
        this.srcGroup = srcGroup;
        this.targetGroup = targetGroup;
        this.srcIdx = srcIdx;
        this.targetIdx = targetIdx;
        this.visible = visible;
    }

    public String getSrcGroup() {
        return srcGroup;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public int getSrcIdx() {
        return srcIdx;
    }

    public int getTargetIdx() {
        return targetIdx;
    }

    public boolean getVisible() {
        return visible;
    }
}
