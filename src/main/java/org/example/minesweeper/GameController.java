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

    private final Game game = new Game();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyComboBox.getItems().addAll(
                "Easy \t| (8x8) \t| 10min",
                "Medium \t| (16x16) \t| 30min",
                "Hard \t| (30x16) \t| 99min"
        );
    }

    public void onDifficultyOptionClicked(ActionEvent actionEvent) {
        ComboBox<String> src = (ComboBox<String>) actionEvent.getSource();
        String opt = src.getSelectionModel().getSelectedItem();

        System.out.println(game.setGridSize(opt));
    }

    public void onStartButtonClicked(ActionEvent actionEvent) {
    }

    public void onRestartButtonClicked(ActionEvent actionEvent) {
    }
}
