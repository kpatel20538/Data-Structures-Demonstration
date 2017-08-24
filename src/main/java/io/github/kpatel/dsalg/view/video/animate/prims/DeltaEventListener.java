package io.github.kpatel.dsalg.view.video.animate.prims;

import io.github.kpatel.dsalg.model.util.Delta;
import javafx.animation.Transition;

public interface DeltaEventListener {
    Transition onIntercept(DotPool dotPool, Delta delta);
}
