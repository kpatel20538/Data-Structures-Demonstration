package io.github.kpatel.dsalg.view.video;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class VideoController {
    @FXML
    private Pane animationPane;
    @FXML
    private Label cuePointName;
    @FXML
    private Label currentTime;
    @FXML
    private Label totalTime;
    @FXML
    private Slider seekBar;
    private Animation animation = new Timeline();
    private TreeMap<Duration, String> cuePoints = new TreeMap<>();

    private ChangeListener<Duration> adjustControls = (observableValue, oldValue, newValue) -> {
        this.cuePointName.setText(getPrevCuePoint(newValue).getValue());
        this.currentTime.setText(toTimestamp(newValue));
        if (!this.seekBar.isValueChanging())
            this.seekBar.setValue(newValue.toSeconds());
    };
    private ChangeListener<Number> adjustTimeline = (observableValue, oldValue, newValue) ->
            getAnimation().jumpTo(Duration.seconds(newValue.doubleValue()));

    /**
     * Toggle Play Pause state for the underlying Animation Object
     */
    @FXML
    protected void playPause(ActionEvent event) {
        switch (getAnimation().getStatus()) {
            case PAUSED:
                getAnimation().play();
                break;
            case RUNNING:
                getAnimation().pause();
                break;
            case STOPPED:
                getAnimation().playFromStart();
                break;
        }
    }

    /**
     * Seek ahead by 30 seconds in the Animation Object
     */
    @FXML
    protected void seekForward(ActionEvent event) {
        Duration totalTime = getAnimation().getTotalDuration();
        Duration shiftedTime = getAnimation().getCurrentTime().add(Duration.seconds(30));
        Duration jumpTime = shiftedTime.compareTo(totalTime) == -1 ? shiftedTime : totalTime;
        getAnimation().jumpTo(jumpTime);
    }

    /**
     * Seek behind by 30 seconds in the Animation Object
     */
    @FXML
    protected void seekReverse(ActionEvent event) {
        Duration shiftedTime = getAnimation().getCurrentTime().subtract(Duration.seconds(30));
        Duration jumpTime = shiftedTime.compareTo(Duration.ZERO) == 1 ? shiftedTime : Duration.ZERO;
        getAnimation().jumpTo(jumpTime);
    }

    /**
     * Skip to the next Cue Point
     */
    @FXML
    protected void seekNextCuePoint(ActionEvent event) {
        Duration currentTime = getAnimation().getCurrentTime();
        getAnimation().jumpTo(getNextCuePoint(currentTime).getKey());
    }

    /**
     * Skip to the previous Cue Point
     */
    @FXML
    protected void seekPrevCuePoint(ActionEvent event) {
        Duration currentTime = getAnimation().getCurrentTime();
        getAnimation().jumpTo(getPrevCuePoint(currentTime).getKey());
    }

    /**
     * Access : Animation Object
     */
    public Animation getAnimation() {
        return animation;
    }

    /**
     * Mutate and Bind : Animation Object
     */
    public void setAnimation(Animation animation) {
        // Unbind and Rebind Step

        this.animation.currentTimeProperty().removeListener(this.adjustControls);
        animation.currentTimeProperty().addListener(this.adjustControls);

        this.seekBar.valueProperty().removeListener(this.adjustTimeline);
        this.seekBar.valueProperty().addListener(this.adjustTimeline);

        // Value Setting
        Duration totalDuration = animation.getTotalDuration();
        this.totalTime.setText(toTimestamp(totalDuration));
        this.seekBar.setMax(totalDuration.toSeconds());

        // Establishing Cue Points
        this.cuePoints.clear();
        for (Map.Entry<String, Duration> entry : animation.getCuePoints().entrySet()) {
            this.cuePoints.put(entry.getValue(), entry.getKey());
        }
        this.cuePoints.putIfAbsent(Duration.ZERO, "---");
        this.cuePoints.putIfAbsent(totalDuration, "---");

        // Clean Up
        this.seekBar.setValue(0);
        this.animation = animation;
    }

    /**
     * Access : Cuepoint Map Object
     */
    public TreeMap<Duration, String> getCuePoints() {
        return cuePoints;
    }


    /**
     * Access: Animation Pane
     */
    public Pane getAnimationPane() {
        return animationPane;
    }

    /**
     * Update bindings
     */
    private void updateBinding() {
        // Updating total duration

    }

    /**
     * Convert Duration Object to String Timestamp
     */
    private String toTimestamp(Duration duration) {
        long seconds = (long) duration.toSeconds();
        long sec = seconds % 60;
        long min = (seconds / 60) % 60;
        return String.format("%02d:%02d", min, sec);
    }

    private Map.Entry<Duration,String> getNextCuePoint(Duration duration){
        return Optional.ofNullable(getCuePoints())
                .map(cp -> cp.higherEntry(duration))
                .orElse(new AbstractMap.SimpleEntry<>(getAnimation().getTotalDuration(), "---"));
    }
    private Map.Entry<Duration,String> getPrevCuePoint(Duration duration){
        return Optional.ofNullable(getCuePoints())
                .map(cp -> cp.lowerEntry(duration))
                .orElse(new AbstractMap.SimpleEntry<>(Duration.ZERO, "---"));
    }
}
