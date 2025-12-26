package org.example.minesweeper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private int cellsVertical = 0;
    private int cellsHorizontal = 0;

    public boolean setGridSize(String optStr) {
        Pattern regex = Pattern.compile("[0-9]+x[0-9]+");
        Matcher matcher = regex.matcher(optStr);

        if (matcher.find()) {
            String[] tmp = matcher.group().split("x");

            System.out.println("Output: " + optStr);
            System.out.println("Group: " + matcher.group());

            setCellsHorizontal(Integer.parseInt(tmp[0]));
            setCellsVertical(Integer.parseInt(tmp[1]));

            return true;
        }

        return false;
    }

    public int getCellsVertical() {
        return cellsVertical;
    }

    public void setCellsVertical(int cellsVertical) {
        this.cellsVertical = cellsVertical;
    }

    public int getCellsHorizontal() {
        return cellsHorizontal;
    }

    public void setCellsHorizontal(int cellsHorizontal) {
        this.cellsHorizontal = cellsHorizontal;
    }
}
