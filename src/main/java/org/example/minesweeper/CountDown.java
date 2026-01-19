package org.example.minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class CountDown {
    private int remainingSeconds;
    private Timeline timeline = null;

    public CountDown(Label output) {
        this(1000, output);
    }

    public CountDown(int startSeconds, Label output) {
        setRemainingSeconds(startSeconds);

        timeline = new Timeline(
                // update evry second
                new KeyFrame(Duration.seconds(1), e -> {
                    setRemainingSeconds(getRemainingSeconds() - 1);
                    output.setText(String.valueOf(getRemainingSeconds()));

                    if (getRemainingSeconds() <= 0) {
                        timeline.stop();
                        output.setText("Done");
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }
}
