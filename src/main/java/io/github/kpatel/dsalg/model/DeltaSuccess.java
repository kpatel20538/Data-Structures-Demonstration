package io.github.kpatel.dsalg.model;

public class DeltaSuccess extends Delta {
    private boolean success;
    public DeltaSuccess(boolean success) {
        super();
        this.success = success;
    }
    public boolean isSuccess(){
        return this.success;
    }
}
