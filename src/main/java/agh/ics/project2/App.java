package agh.ics.project2;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class App extends Application {
    GridPane grid = new GridPane();
    Scene mainScene = new Scene(this.grid, 630,630);
    UltimateTicTacToe uttt = new UltimateTicTacToe();
    private final Map<Positions, GridPane> tictactoeGrids = new EnumMap<>(Positions.class);

    public void init(){
        Arrays.stream(Positions.values()).forEach(v-> tictactoeGrids.put(v, new GridPane()));
    }

    public void start(Stage primaryStage) {
        updateGrid();

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Ultimate Tic Tac Toe");
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void updateGrid() {
        clearGrid(grid);
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                updateOneTicTacToe(Positions.getPositionFromCords(y, x));
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                updateOneMainCell(y, x);
            }
        }

        updateGridConstraints(grid, 210);
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);

    }

    private void updateOneMainCell(int y, int x){
        HBox hbox = new HBox(tictactoeGrids.get(Positions.getPositionFromCords(y,x)));
        hbox.setAlignment(Pos.CENTER);
        grid.add(hbox, x, y, 1, 1);
        GridPane.setHalignment(hbox, HPos.CENTER);
    }

    private void updateOneTicTacToe(Positions position){
        GridPane grid = tictactoeGrids.get(position);
        clearGrid(grid);

        if (uttt.isTicTacToeFinished(position)){
            Signs winner = uttt.getTicTacToeWinner(position);
            Color backgroundColor = Color.WHITESMOKE;
            Label label = new Label(winner.getText());
            label.setFont(new Font(150));
            HBox hbox = new HBox(label);
            hbox.setAlignment(Pos.CENTER);
            hbox.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
            grid.add(hbox, 0, 0, 1, 1);
            GridPane.setHalignment(hbox, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(210));
            grid.getColumnConstraints().add(new ColumnConstraints(210));
        }else{
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    updateOneTicTacToeCell(y,x,grid, position);
                }
            }
            updateGridConstraints(grid, 70);
        }
        grid.setGridLinesVisible(true);
    }

    private void updateOneTicTacToeCell(int y, int x, GridPane grid, Positions gridPosition){
        Color backgroundColor = Color.WHITESMOKE;
        if (uttt.currentTicTacToe==gridPosition && !uttt.isFinished)
            backgroundColor = Color.MEDIUMVIOLETRED;

        Positions cellPosition = Positions.getPositionFromCords(y,x);
        HBox hbox = new HBox(new Label(uttt.getSignFromTicTacToe(gridPosition, cellPosition).getText()));
        if (uttt.getSignFromTicTacToe(gridPosition, cellPosition) == Signs.Nothing && !uttt.isFinished){
            hbox.setOnMouseClicked(click -> {
                if ((uttt.currentTicTacToe == null || uttt.currentTicTacToe == gridPosition)){
                    Signs currentPlayer = uttt.getCurrentSign();
                    uttt.addSignToTicTacToe(gridPosition, cellPosition);
                    uttt.checkIfFinished(gridPosition, currentPlayer);
                    updateGrid();
                }
            });
        }

        hbox.setAlignment(Pos.CENTER);
        hbox.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(hbox, x, y, 1, 1);
        GridPane.setHalignment(hbox, HPos.CENTER);
    }

    private void updateGridConstraints(GridPane grid, int size) {
        for (int y = 0; y < 3; y++) {
            grid.getRowConstraints().add(new RowConstraints(size));
        }
        for (int x = 0; x < 3; x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(size));
        }
    }

    private void clearGrid(GridPane grid){
        grid.getChildren().clear();
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.setGridLinesVisible(false);
    }

}