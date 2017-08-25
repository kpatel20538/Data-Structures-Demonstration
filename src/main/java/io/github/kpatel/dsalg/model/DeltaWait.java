package io.github.kpatel.dsalg.model;

import javafx.util.Duration;

public class DeltaWait extends Delta {
    private final Duration duration;

    public DeltaWait() {
        this(Duration.seconds(1));
    }

    public DeltaWait(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

}
