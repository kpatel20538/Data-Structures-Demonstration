package io.github.kpatel.dsalg.doc;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public abstract class Demonstration {
    public String fxmlUrl;
    public String name;
    public Demonstration(String fxmlUrl,String name){
        this.fxmlUrl = fxmlUrl;
        this.name = name;
    }

    public abstract Timeline makeAnimation(Pane animationPane);
}
