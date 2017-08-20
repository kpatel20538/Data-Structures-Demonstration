package io.github.kpatel.dsalg.video;

import javafx.animation.Timeline;
import javafx.collections.ObservableMap;
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

    /** Toggle Play Pause state for the underlying Timeline Object */
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

    /** Seek ahead by 30 seconds in the Timeline Object */
    @FXML protected void seekForward(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration totalTime = getTimeline().getTotalDuration();
        Duration shiftedTime = currentTime.add(Duration.seconds(30));
        Duration jumpTime = shiftedTime.compareTo(totalTime) == -1 ? shiftedTime:totalTime;
        getTimeline().jumpTo(jumpTime);
    }

    /** Seek behind by 30 seconds in the Timeline Object */
    @FXML protected void seekReverse(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration shiftedTime = currentTime.subtract(Duration.seconds(30));
        Duration jumpTime = shiftedTime.compareTo(Duration.ZERO) == 1 ? shiftedTime:Duration.ZERO;
        getTimeline().jumpTo(jumpTime);
    }

    /** Skip to the next Cue Point */
    @FXML protected void seekNextCuePoint(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration shiftedTime = this.cuePoints.higherKey(currentTime);
        getTimeline().jumpTo(shiftedTime);
    }

    /** Skip to the previous Cue Point */
    @FXML protected void seekPrevCuePoint(ActionEvent event){
        Duration currentTime = getTimeline().getCurrentTime();
        Duration shiftedTime = this.cuePoints.lowerKey(currentTime);
        getTimeline().jumpTo(shiftedTime);
    }

    /** Access : Timeline Object */
    public Timeline getTimeline() {
        return timeline;
    }

    /** Mutate and Bind : Timeline Object */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
        updateBinding();
    }

    /** Access : Cuepoint Map Object */
    public TreeMap<Duration,String> getCuePoints() {
        return cuePoints;
    }

    /** Mutate : Cuepoint Map Object */
    public void setCuePoints(ObservableMap<String,Duration> cuepoints) {
        this.cuePoints.clear();
        for(Map.Entry<String,Duration> entry: getTimeline().getCuePoints().entrySet()){
            this.cuePoints.put(entry.getValue(),entry.getKey());
        }
    }

    /** Access: Animation Pane */
    public Pane getAnimationPane(){
        return animationPane;
    }

    /** Update bindings */
    private void updateBinding(){
        Duration total = getTimeline().getTotalDuration();
        this.totalTime.setText(toTimestamp(total));
        this.seekBar.setMax(total.toSeconds());
        this.seekBar.setValue(0);
        setCuePoints(getTimeline().getCuePoints());
        getTimeline().currentTimeProperty().addListener((observableValue, oldValue, newValue) -> {
            this.currentTime.setText(toTimestamp(newValue));
            this.cuePointName.setText(getCuePoints().lowerEntry(newValue).getValue());
            if (!this.seekBar.isValueChanging())
                this.seekBar.setValue(newValue.toSeconds());
        });
        this.seekBar.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            getTimeline().jumpTo(Duration.seconds(newValue.doubleValue()));
        });
    }

    /** Convert Duration Object to String Timestamp */
    private String toTimestamp(Duration duration){
        long seconds = (long) duration.toSeconds();
        long sec = seconds % 60;
        long min = (seconds / 60) % 60;
        return String.format("%02d:%02d", min, sec);
    }
}
