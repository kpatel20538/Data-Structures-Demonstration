package io.github.kpatel.dsalg.doc;

import javafx.animation.Animation;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

import java.net.URL;

public abstract class Demonstration{
    private final String name;
    private final URL fxmlPath;

    public Demonstration( String name, String fxmlPath ){
        this.name = name;
        this.fxmlPath = getClass().getResource("/io/github/kpatel/dsalg/doc/introduction.fxml");
    }

    public String getName() {
        return name;
    }

    public URL getFxmlPath() {
        return fxmlPath;
    }

    public abstract Animation makeAnimation(Pane animationPane);
    public TreeItem<Demonstration> getItem(){
        return new TreeItem<>(this);
    }
}
