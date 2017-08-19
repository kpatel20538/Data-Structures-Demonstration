package io.github.kpatel.dsalg.video;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class VideoController {
    @FXML private Pane animationPane;
    @FXML private Label cuePointName;
    @FXML private Label currentTime;
    @FXML private Label totalTime;
    @FXML private Slider seekBar;

    @FXML protected void playPause(ActionEvent event){
        System.out.println("Play Pause");
    }
    @FXML protected void seekForward(ActionEvent event){
        System.out.println("Seek Forward");
    }
    @FXML protected void seekReverse(ActionEvent event){
        System.out.println("Seek Reverse");
    }
    @FXML protected void seekNextCuePoint(ActionEvent event){
        System.out.println("Seek Next");
    }
    @FXML protected void seekPrevCuePoint(ActionEvent event){
        System.out.println("Seek Prev");
    }

}
