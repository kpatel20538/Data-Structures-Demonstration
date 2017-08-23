package io.github.kpatel.dsalg.view.video;

import javafx.animation.Animation;
import javafx.beans.property.ObjectProperty;
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
    private Animation animation;
    private TreeMap<Duration, String> cuePoints = new TreeMap<>();

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
        this.animation = animation;
        updateBinding();
    }

    /**
     * Access : Cuepoint Map Object
     */
    public TreeMap<Duration, String> getCuePoints() {
        return cuePoints;
    }

    /**
     * Mutate : Cuepoint Map Object
     */
    public void setCuePoints() {
        this.cuePoints.clear();

        for (Map.Entry<String, Duration> entry : getAnimation().getCuePoints().entrySet()) {
            this.cuePoints.put(entry.getValue(), entry.getKey());
        }
        this.cuePoints.put(Duration.ZERO, "---");
        this.cuePoints.put(getAnimation().getTotalDuration(), "---");
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
        Duration total = getAnimation().getTotalDuration();
        this.totalTime.setText(toTimestamp(total));
        this.seekBar.setMax(total.toSeconds());
        
        // Update Cue points and Current Time
        setCuePoints();
        getAnimation().currentTimeProperty().addListener((observableValue, oldValue, newValue) -> {
            this.cuePointName.setText(getPrevCuePoint(newValue).getValue());
            this.currentTime.setText(toTimestamp(newValue));
            if (!this.seekBar.isValueChanging())
                this.seekBar.setValue(newValue.toSeconds());
        });
        this.seekBar.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            getAnimation().jumpTo(Duration.seconds(newValue.doubleValue()));
        });

        // Set time to Start
        this.seekBar.setValue(0);
        getAnimation().stop();
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
