package org.example.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public Label timeLabel = new Label();
    public GridPane gamePane = new GridPane();
    public ComboBox<String> difficultyComboBox = new ComboBox<>();

    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // creat default Pane & Grid & CountDown
        game = new Game(gamePane, new CountDown(timeLabel));

        // add Difficulty Options
        difficultyComboBox.getItems().addAll(
                "Easy \t| (8x8), (10 Mines) \t| 10min",
                "Medium \t| (16x16), (40 Mines) \t| 30min",
                "Hard \t| (30x16), (99 Mines) \t| 99min"
        );
    }

    public void onDifficultyOptionClicked(ActionEvent actionEvent) {
        ComboBox<String> src = (ComboBox<String>) actionEvent.getSource();
        String opt = src.getSelectionModel().getSelectedItem();

        // apply option
        if (game.setOptions(opt)) {
            game.setGameGrid(
                    game.getNumCols(),
                    game.getNumRows()
            );

            game.clearGamePane();
            game.addCells();
            // would continue otherwise
            game.getCountDown().stop();
            // would prevent setMines
            game.setClickCount(0);
        }
    }

    public void onStartButtonClicked(ActionEvent actionEvent) {
    }

    public void onRestartButtonClicked(ActionEvent actionEvent) {

    }
}
