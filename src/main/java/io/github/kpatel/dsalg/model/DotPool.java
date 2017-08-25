package io.github.kpatel.dsalg.model;

import io.github.kpatel.dsalg.view.controls.BubbleRecord;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Optional;

public class DotPool {
    private final static Duration TIME_STEP = Duration.seconds(1);

    private final HashMap<String,DotGroup> dotGroups;
    private final HashMap<String,Node> markers;
    private DeltaEventListener preInterceptor;
    private DeltaEventListener postInterceptor;

    public DotPool() {
        dotGroups = new HashMap<>();
        markers = new HashMap<>();
        preInterceptor = DotPool::defaultInterceptor;
        postInterceptor = DotPool::defaultInterceptor;
    }

    public HashMap<String, DotGroup> getDotGroups() {
        return dotGroups;
    }
    public HashMap<String, Node> getMarkers() {
        return markers;
    }

    public Transition applyDeltas(Iterable<Delta> procedure){
        SequentialTransition sequentialTransition = new SequentialTransition(this.fadeIn());
        Transition preTransition;
        Transition transition;
        Transition postTransition;
        for (Delta delta : procedure) {
            preTransition = preInterceptor.onIntercept(this,delta);
            if (delta instanceof DeltaSwap) {
                DeltaSwap swap = (DeltaSwap) delta;
                transition = this.swapDots(
                        swap.getSrcGroup(),swap.getTargetGroup(),
                        swap.getSrcIdx(),swap.getTargetIdx()
                );
            } else if (delta instanceof DeltaMarker){
                DeltaMarker move = (DeltaMarker) delta;
                transition = this.moveToDot(move.getGroup(),move.getIdx(),15,-20,move.getMarker());
            } else if (delta instanceof DeltaArrow){
                //TODO: Delta Arrow
                transition= new PauseTransition(Duration.ZERO);
            } else if (delta instanceof DeltaFlip){
                DeltaFlip flip = (DeltaFlip) delta;
                Optional<Node> node = getDot(flip.getGroup(),flip.getIdx()).getNode();
                transition = node.isPresent() && node.get() instanceof BubbleRecord ?
                        ((BubbleRecord) node.get()).setFlip(flip.isFlippedUp()) :
                        new PauseTransition(Duration.ZERO);
            } else if (delta instanceof DeltaWait){
                transition= new PauseTransition(((DeltaWait) delta).getDuration());
            } else{
                transition = new PauseTransition(Duration.ZERO);
            }
            postTransition = postInterceptor.onIntercept(this,delta);
            sequentialTransition.getChildren().addAll(preTransition,transition,postTransition);
        }
        return sequentialTransition;
    }

    public Transition swapDots(String src, String target,int srcIdx, int targetIdx) {
        Dot left = getDot(src,srcIdx);
        Dot right = getDot(target,targetIdx);
        Transition ltt = left.getNode().map(node -> {
            TranslateTransition tt = new TranslateTransition(TIME_STEP, node);
            tt.fromXProperty().bind(left.xProperty());
            tt.fromYProperty().bind(left.yProperty());
            tt.toXProperty().bind(right.xProperty());
            tt.toYProperty().bind(right.yProperty());
            return (Transition) tt;
        }).orElse(new PauseTransition(Duration.ZERO));
        Transition rtt = right.getNode().map(node -> {
            TranslateTransition tt = new TranslateTransition(TIME_STEP, node);
            tt.fromXProperty().bind(right.xProperty());
            tt.fromYProperty().bind(right.yProperty());
            tt.toXProperty().bind(left.xProperty());
            tt.toYProperty().bind(left.yProperty());
            return (Transition) tt;
        }).orElse(new PauseTransition(Duration.ZERO));
        left.setNode(right.setNode(left.getNode()));
        return new ParallelTransition(ltt, rtt);
    }

    public Transition fadeIn() {
        ParallelTransition pt = new ParallelTransition();
        for(DotGroup dotGroup: getDotGroups().values()){
            pt.getChildren().add(dotGroup.fadeIn());
        }
        return pt;
    }

    public Transition moveToDot(String group, int i,double xOffset,double yOffset, String marker){
        return moveToDot(group,i,xOffset,yOffset,getMarkers().get(marker));
    }

    public Transition moveToDot(String group, int i,double xOffset,double yOffset, Node node){
        TranslateTransition tt = new TranslateTransition(TIME_STEP, node);
        Dot dot = getDot(group,i);
        tt.toXProperty().bind(dot.xProperty().add(xOffset));
        tt.toYProperty().bind(dot.yProperty().add(yOffset));
        return tt;
    }

    public Dot getDot(String group, int idx) {
        return getDotGroups().get(group).getDots().get(idx);
    }

    public Dot addDot(String group, double x, double y){
        return getDotGroups().get(group).addDot(x,y);
    }

    public DeltaEventListener getPreInterceptor() {
        return preInterceptor;
    }

    public void setPreInterceptor(DeltaEventListener preInterceptor) {
        this.preInterceptor = preInterceptor;
    }

    public DeltaEventListener getPostInterceptor() {
        return postInterceptor;
    }

    public void setPostInterceptor(DeltaEventListener postInterceptor) {
        this.postInterceptor = postInterceptor;
    }

    public static Transition defaultInterceptor(DotPool dotPool, Delta delta){
        return new PauseTransition(Duration.ZERO);
    }
}
