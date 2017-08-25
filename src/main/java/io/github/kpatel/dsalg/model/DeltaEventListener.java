package io.github.kpatel.dsalg.model;

import javafx.animation.Transition;

public interface DeltaEventListener {
    Transition onIntercept(DotPool dotPool, Delta delta);
}
