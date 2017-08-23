package io.github.kpatel.dsalg.model.util;

public class DeltaSwap extends Delta{
    private final int left;
    private final int right;
    public DeltaSwap(int left,int right){
        this.left = left; this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public String toString() {
        return String.format("%s <--> %s", getLeft(),getRight());
    }
}
