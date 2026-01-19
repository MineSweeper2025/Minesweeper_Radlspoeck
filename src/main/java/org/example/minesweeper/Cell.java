package org.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Cell extends Button {
    private boolean isMine = false;
    private boolean isMarked = false;
    private boolean isExposed = false;
    private int numSurMines = 0;

    private Game game;

    public Cell(Game game) {
        setGame(game);
        // set for x/y in px
        setPrefSize(30, 30);
        getStyleClass().add("cell-style-default");
        setOnMouseClicked(this::onClick);
    }

    private void onClick(MouseEvent mouseEvent) {
        switch (mouseEvent.getButton()) {
            case PRIMARY:
                // get pos
                Integer posY = GridPane.getRowIndex(this);
                Integer posX = GridPane.getColumnIndex(this);

                // on first Click
                if (getGame().getClickCount() == 0) {
                    //setExposed(true);
                    getGame().setMines(posX, posY);
                    getGame().getCountDown().start();
                    // skip this next time
                    getGame().setClickCount(1);
                }

                if (!isMarked()) {
                    if (!isMine()) {
                        // pars first row/col position to 0
                        getGame().revealCell(posX == null ? 0 : posX, posY == null ? 0 : posY);
                        return;
                    }

                    getGame().showEndScreen("You Lose");
                }
                break;
            case SECONDARY:
                setMarked(!isMarked());
                break;
        }
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        this.isMine = mine;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        this.isMarked = marked;

        getStyleClass().removeAll("cell-style-marked", "cell-style-default");
        getStyleClass().add(isMarked() ? "cell-style-marked" : "cell-style-default");
        setText(isMarked() ? "!" : "");
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        this.isExposed = exposed;

        getStyleClass().add(isMine() ? "cell-style-mine" : "cell-style-exposed");
        setText(String.valueOf(getNumSurMines()));
        setDisable(true);
    }

    public int getNumSurMines() {
        return numSurMines;
    }

    public void setNumSurMines(int numSurMines) {
        this.numSurMines = numSurMines;
    }

    public void incNumSurMines() {
        setNumSurMines(getNumSurMines() + 1);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
