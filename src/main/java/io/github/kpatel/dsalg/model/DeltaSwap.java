package io.github.kpatel.dsalg.model;

public class DeltaSwap extends Delta {
    private final String srcGroup;
    private final String targetGroup;
    private final int srcIdx;
    private final int targetIdx;

    public DeltaSwap(String group, int srcIdx, int targetIdx) {
        this(group,group,srcIdx,targetIdx);
    }

    public DeltaSwap(String srcGroup, String targetGroup, int srcIdx, int targetIdx) {
        this.srcGroup = srcGroup;
        this.targetGroup = targetGroup;
        this.srcIdx = srcIdx;
        this.targetIdx = targetIdx;
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
}
