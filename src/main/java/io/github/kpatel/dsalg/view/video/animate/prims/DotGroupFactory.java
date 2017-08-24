package io.github.kpatel.dsalg.view.video.animate.prims;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class DotGroupFactory {
    public static DotGroup makePointGroup(Pane animationPane, double x, double y, double width, double height){
        DotGroup dotGroup = new DotGroup(animationPane, x, y, width, height);
        dotGroup.getDots().add(new Dot(dotGroup, 0.5, 0.5));
        return dotGroup;
    }

    public static DotGroup makeHorizontalGroup(Pane animationPane, double x, double y, double width, double height, int size){
        DotGroup dotGroup = new DotGroup(animationPane, x, y, width, height);
        ArrayList<Dot> dots = dotGroup.getDots();
        for (int i = 0; i < size; i++) {
            dots.add(new Dot(dotGroup, ((double) i) / size, 0.5));
        }
        return dotGroup;
    }

    public static DotGroup makeVerticalGroup(Pane animationPane, double x, double y, double width, double height, int size){
        DotGroup dotGroup = new DotGroup(animationPane, x, y, width, height);
        ArrayList<Dot> dots = dotGroup.getDots();
        for (int i = 0; i < size; i++) {
            dots.add(new Dot(dotGroup, 0.5, ((double) i) / size));
        }
        return dotGroup;
    }

    public static DotGroup makeGridGroup(Pane animationPane, double x, double y, double width, double height, int rows, int cols){
        DotGroup dotGroup = new DotGroup(animationPane, x, y, width, height);
        ArrayList<Dot> dots = dotGroup.getDots();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                dots.add(new Dot(dotGroup, ((double) i) / rows, ((double) j) / cols));
        return dotGroup;
    }

    public static DotGroup makeTreeGroup(Pane animationPane, double x, double y, double width, double height, int maxDepth){
        DotGroup dotGroup = new DotGroup(animationPane, x, y, width, height);
        ArrayList<Dot> dots = dotGroup.getDots();
        int girth = 1;
        for(int depth = 0; depth < maxDepth; depth++){
            for(int j = 0; j < girth; j++){
                Dot dot = new Dot(dotGroup,(2.0*j+1.0)/(2*girth),((double) depth) / ((double) maxDepth));
                dots.add(dot);
            }
            girth *= 2;
        }
        return dotGroup;
    }
}
