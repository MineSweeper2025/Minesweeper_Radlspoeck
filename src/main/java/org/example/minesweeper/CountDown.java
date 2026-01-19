package org.example.minesweeper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class CountDown {
    private int remainingSeconds = 0;
    private int initialSeconds = 0;
    private Timeline timeline = null;

    public CountDown(Label output) {
        // default time in easy mode
        this(600, output);
    }

    public CountDown(int startSeconds, Label output) {
        setRemainingSeconds(startSeconds);
        setInitialSeconds(startSeconds);

        timeline = new Timeline(
                // update evry second
                new KeyFrame(Duration.seconds(1), e -> {
                    setRemainingSeconds(getRemainingSeconds() - 1);
                    output.setText(getRemainingSeconds() + " sec");

                    if (getRemainingSeconds() <= 0) {
                        timeline.stop();
                        output.setText("Time is up");
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
        setInitialSeconds(remainingSeconds);
    }

    public int getInitialSeconds() {
        return initialSeconds;
    }

    public void setInitialSeconds(int initialSeconds) {
        this.initialSeconds = initialSeconds;
    }
}
