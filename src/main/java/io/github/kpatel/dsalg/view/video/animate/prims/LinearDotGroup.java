package io.github.kpatel.dsalg.view.video.animate.prims;


import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class LinearDotGroup extends DotGroup{
    public LinearDotGroup(Pane animationPane, double x, double y, double w, double h, int size ) {
        super(animationPane, x, y, w, h, size);
    }

    @Override
    protected List<Dot> assignDots(int size) {
        ArrayList<Dot> dots = new ArrayList<>(size);
        for(int i = 0;i < size; i++){
            dots.add(i,new Dot(this,((double) i)/size,0.5));
        }
        return dots;
    }
}
