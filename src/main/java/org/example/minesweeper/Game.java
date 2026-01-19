package org.example.minesweeper;

import javafx.scene.layout.GridPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private int numCols = 0;
    private int numRows = 0;
    private int numMines = 0;
    private int clickCount = 0;
    private String prevOpt = "";

    private CountDown countDown;
    private GridPane gamePane;
    private Cell[][] gameGrid;

    public Game(GridPane gamePane, CountDown countDown) {
        setGamePane(gamePane);
        setCountDown(countDown);

        // default
        setNumCols(8);
        setNumRows(8);
        setNumMines(20);
        setGameGrid(8, 8);

        // draw
        addCells();
    }

    public void addCells() {
        for (int y = 0; y < getNumRows(); ++y) {
            for (int x = 0; x < getNumCols(); ++x) {
                Cell tmp = new Cell(this);

                // add to gamepane & gamegrid at pos xy
                getGamePane().add(tmp, x, y);
                getGameGrid()[x][y] = tmp;
            }
        }
    }

    public void revealCell(int posX, int posY) {
        // check for out of Bounds
        if (posX < 0 || posX >= getNumCols() || posY < 0 || posY >= getNumRows()) {
            return;
        }

        Cell tmp = getGameGrid()[posX][posY];

        if (tmp.isExposed() || tmp.isMarked()) {
            return;
        }

        tmp.setExposed(true);
        // check because
        // if last unrevealed cell is surrounded by mines it wont trigger
        checkForWin();

        if (tmp.getNumSurMines() > 0) {
            return;
        }

        // reveal surounding cells
        for (int y = posY - 1; y <= posY + 1; y++) {
            for (int x = posX - 1; x <= posX + 1; x++) {
                // skip this cell
                if (!(x == posX && y == posY)) {
                    revealCell(x, y);
                }
            }
        }
    }

    public void setMines(int ignoreX, int ignoreY) {
        int count = 0;

        while (count < getNumMines()) {
            // get random pos in grid
            int posX = (int) (Math.random() * getNumCols());
            int posY = (int) (Math.random() * getNumRows());

            if (posX == ignoreX && posY == ignoreY) {
                continue;
            }

            Cell tmp = getGameGrid()[posX][posY];

            if (!tmp.isMine()) {
                tmp.setMine(true);
                ++count;

                // set numSurMines for surrounding mines
                for (int y = posY - 1; y <= posY + 1; y++) {
                    for (int x = posX - 1; x <= posX + 1; x++) {
                        // check Bounds
                        if (x >= 0 && x < getNumCols() && y >= 0 && y < getNumRows()) {
                            // skip this cell
                            if (!(x == posX && y == posY)) {
                                getGameGrid()[x][y].incNumSurMines();
                            }
                        }
                    }
                }
            }
        }
    }

    public void showEndScreen(String str) {
        Cell tmp = new Cell(this);
        tmp.setText(str);
        tmp.setPrefSize(getGamePane().getWidth(), getGamePane().getHeight());
        tmp.getStyleClass().add("cell-style-mine");
        tmp.setDisable(true);

        clearGamePane();
        getGamePane().add(tmp, 0, 0);
        getCountDown().stop();
    }

    public void checkForWin() {
        for (int y = 0; y < getNumRows(); y++) {
            for (int x = 0; x < getNumCols(); x++) {
                Cell tmp = getGameGrid()[x][y];

                if (!tmp.isMine() && !tmp.isExposed()) {
                    return;
                }
            }
        }

        showEndScreen("You Win");
    }

    public boolean setOptions(String optStr) {
        // rows, cols, mines, time (min)
        Pattern regex = Pattern.compile(
                "([0-9]+)x([0-9]+).*?\\(([0-9]+) Mines\\).*?\\| ([0-9]+)min"
        );
        Matcher matcher = regex.matcher(optStr);

        if (matcher.find()) {
            setNumCols(Integer.parseInt(matcher.group(1)));
            setNumRows(Integer.parseInt(matcher.group(2)));
            setNumMines(Integer.parseInt(matcher.group(3)));
            // convert min to sec
            getCountDown().setRemainingSeconds(Integer.parseInt(matcher.group(4)) * 60);

            // save for restart button
            setPrevOpt(optStr);

            System.out.printf(
                    "Set Cols: %d, Set Rows: %d, Set Mines %d, Set RemainingSeconds: %d\n",
                    getNumCols(),
                    getNumRows(),
                    getNumMines(),
                    getCountDown().getRemainingSeconds()
            );

            return true;
        }

        return false;
    }

    public void setup(String optStr) {
        if (setOptions(optStr)) {
            setGameGrid(
                    getNumCols(),
                    getNumRows()
            );

            clearGamePane();
            addCells();
            // would continue otherwise
            getCountDown().stop();
            // would prevent setMines
            setClickCount(0);
        }
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    public void setGamePane(GridPane gamePane) {
        this.gamePane = gamePane;
    }

    public GridPane getGamePane() {
        return gamePane;
    }

    public void clearGamePane() {
        getGamePane().getChildren().clear();
    }

    public Cell[][] getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(int numCols, int numRows) {
        this.gameGrid = new Cell[numCols][numRows];
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public CountDown getCountDown() {
        return countDown;
    }

    public void setCountDown(CountDown countDown) {
        this.countDown = countDown;
    }

    public String getPrevOpt() {
        return prevOpt;
    }

    public void setPrevOpt(String prevOpt) {
        this.prevOpt = prevOpt;
    }
}
