package org.example.minesweeper;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private int cols = 0;
    private int rows = 0;

    private final GridPane gamePane;

    public Game(GridPane gamePane) {
        this.gamePane = gamePane;

        // default
        setCols(8);
        setRows(8);
    }

    public void addCells() {
        for (int y = 0; y < getCols(); ++y) {
            for (int x = 0; x < getRows(); ++x) {
                Button tmp = new Button();

                // tmp -> todo: styleid, onAction
                tmp.setText(x + ", " + y);

                getGamePane().add(tmp, y, x);
            }
        }
    }

    public boolean setGridSize(String optStr) {
        Pattern regex = Pattern.compile("[0-9]+x[0-9]+");
        Matcher matcher = regex.matcher(optStr);

        if (matcher.find()) {
            String[] tmp = matcher.group().split("x");

            setCols(Integer.parseInt(tmp[0]));
            setRows(Integer.parseInt(tmp[1]));

            return true;
        }

        return false;
    }

    public void clearGrid() {
        getGamePane().getChildren().clear();
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public GridPane getGamePane() {
        return gamePane;
    }
}
