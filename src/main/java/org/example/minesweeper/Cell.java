package org.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Cell extends Button {
    private boolean isMine = false;
    private boolean isMarked = false;
    private boolean isExposed = false;

    public Cell(boolean isMine) {
        this.setMine(isMine);
        // set for x/y in px
        this.setPrefSize(30, 30);
        this.getStyleClass().add("cell-style-default");
        this.setOnMouseClicked(this::onClick);
    }

    private void onClick(MouseEvent mouseEvent) {
        switch (mouseEvent.getButton()) {
            // leftclick
            case MouseButton.PRIMARY -> {
                if (this.isMine()) {
                    this.getStyleClass().add("cell-style-mine");
                    // end game
                } else {
                    this.getStyleClass().add("cell-style-exposed");
                }

                this.setDisable(true);
                this.setExposed(true);
            }
            // rightclick
            case MouseButton.SECONDARY -> {
                if (this.isMarked) {
                    this.getStyleClass().add("cell-style-default");
                    this.setMarked(false);
                } else {
                    this.getStyleClass().add("cell-style-marked");
                    this.setMarked(true);
                }
            }
        }
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }
}
