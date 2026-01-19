package org.example.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public Label timeLabel = new Label();
    public GridPane gamePane = new GridPane();
    public ComboBox<String> difficultyComboBox = new ComboBox<>();

    private Game game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // load cell stylesheet
        gamePane.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("stylesheets/cell_style.css")).toExternalForm()
        );

        // creat default gamePane Grid
        game = new Game(gamePane);
        game.addCells();

        // add Difficulty Options
        difficultyComboBox.getItems().addAll(
                "Easy \t| (8x8) \t| 10min",
                "Medium \t| (16x16) \t| 30min",
                "Hard \t| (30x16) \t| 99min"
        );
    }

    public void onDifficultyOptionClicked(ActionEvent actionEvent) {
        // get new option
        ComboBox<String> src = (ComboBox<String>) actionEvent.getSource();
        String opt = src.getSelectionModel().getSelectedItem();

        // apply option
        if (game.setGridSize(opt)) {
            game.clearGrid();
            game.addCells();
        }
    }

    public void onStartButtonClicked(ActionEvent actionEvent) {
    }

    public void onRestartButtonClicked(ActionEvent actionEvent) {
    }
}
