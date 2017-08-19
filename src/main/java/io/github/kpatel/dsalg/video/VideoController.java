package io.github.kpatel.dsalg.video;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Map;
import java.util.TreeMap;

public class VideoController {
    @FXML private Pane animationPane;
    @FXML private Label cuePointName;
    @FXML private Label currentTime;
    @FXML private Label totalTime;
    @FXML private Slider seekBar;
    private Timeline timeline;
    private TreeMap<Duration,String> cuePoints = new TreeMap<>();

    @FXML protected void playPause(ActionEvent event){
        switch (getTimeline().getStatus()){
            case PAUSED:
                getTimeline().play();
                break;
            case RUNNING:
                getTimeline().pause();
                break;
            case STOPPED:
                getTimeline().playFromStart();
                break;
        }
    }
    @FXML protected void seekForward(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration totalTime = getTimeline().getTotalDuration();
        Duration shiftedTime = currentTime.add(Duration.seconds(30));
        Duration jumpTime = shiftedTime.compareTo(totalTime) == -1 ? shiftedTime:totalTime;
        getTimeline().jumpTo(jumpTime);
    }
    @FXML protected void seekReverse(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration shiftedTime = currentTime.subtract(Duration.seconds(30));
        Duration jumpTime = shiftedTime.compareTo(Duration.ZERO) == 1 ? shiftedTime:Duration.ZERO;
        getTimeline().jumpTo(jumpTime);
    }
    @FXML protected void seekNextCuePoint(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration shiftedTime = this.cuePoints.lowerKey(currentTime);
        getTimeline().jumpTo(shiftedTime);
    }
    @FXML protected void seekPrevCuePoint(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration shiftedTime = this.cuePoints.higherKey(currentTime);
        getTimeline().jumpTo(shiftedTime);
    }


    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
        Duration total = this.timeline.getTotalDuration();
        this.totalTime.setText(toTimestamp(total));
        this.seekBar.setMax(total.toSeconds());
        this.seekBar.setValue(0);
        this.cuePoints.clear();
        for(Map.Entry<String,Duration> entry: this.timeline.getCuePoints().entrySet()){
            this.cuePoints.put(entry.getValue(),entry.getKey());
        }
        this.timeline.currentTimeProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    this.currentTime.setText(toTimestamp(newValue));
                    this.cuePointName.setText(this.cuePoints.lowerEntry(newValue).getValue());
                    if (!this.seekBar.isValueChanging())
                        this.seekBar.setValue(newValue.toSeconds());
                }
        );
        this.seekBar.valueProperty().addListener(
                (observableValue, oldValue, newValue) ->
                    this.timeline.jumpTo(Duration.seconds(newValue.doubleValue())
                )
        );


    }



    private String toTimestamp(Duration duration){
        long seconds = (long) duration.toSeconds();
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        return String.format("%02d:%02d", m,s);
    }
}
