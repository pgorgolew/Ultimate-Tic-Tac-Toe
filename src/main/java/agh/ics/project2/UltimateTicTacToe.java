package agh.ics.project2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UltimateTicTacToe {
    private Signs currentSign = Signs.X;
    private Map<Positions, TicTacToe> ticTacToes = new EnumMap<>(Positions.class);
    public Positions currentTicTacToe = null;
    public boolean isFinished = false;
    private Signs winner = Signs.Nothing;

    public Signs getCurrentSign() {
        return currentSign;
    }

    public Signs getTicTacToeWinner(Positions position) {
        return ticTacToes.get(position).getWinner();
    }

    public boolean isTicTacToeFinished(Positions position){
        return ticTacToes.get(position).isFinished;
    }


    public UltimateTicTacToe(){
        Arrays.stream(Positions.values()).forEach(v-> ticTacToes.put(v, new TicTacToe()));
    }

    public Signs getSignFromTicTacToe(Positions ticTacToePosition, Positions cellPosition){
        return ticTacToes.get(ticTacToePosition).getSignFromCell(cellPosition);
    }

    public void addSignToTicTacToe(Positions ticTacToePosition, Positions cellPosition){
        ticTacToes.get(ticTacToePosition).addSign(currentSign, cellPosition);
        currentSign = currentSign.getNextSign();
        currentTicTacToe = cellPosition;

        if (ticTacToes.get(cellPosition).isFinished)
            currentTicTacToe = null;

        System.out.println(currentTicTacToe);

    }

    public void checkIfFinished(Positions position, Signs sign){
        if (getTicTacToeWinner(position) == Signs.Nothing){
            return;
        }

        checkRow(position);
        checkColumn(position);
        checkLeftCross(position);
        checkRightCross(position);

        if (isFinished)
            winner=sign;
        else
            checkIfNoWinner();
    }

    private void checkIfNoWinner(){
        int takenPositions = (int) ticTacToes.values().stream().filter(e -> e.isFinished).count();
        if (takenPositions==9)
            isFinished = true;
    }

    private void checkRow(Positions position){
        int firstRowInt = (position.positionNumber / 3) * 3;

        if (ticTacToes.get(Positions.getPositionFromNumber(firstRowInt)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(firstRowInt+1)).getWinner() &&
                ticTacToes.get(Positions.getPositionFromNumber(firstRowInt)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(firstRowInt+2)).getWinner()){
            isFinished=true;
        }
    }

    private void checkColumn(Positions position){
        int firstCol = position.positionNumber % 3;

        if (ticTacToes.get(Positions.getPositionFromNumber(firstCol)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(firstCol+3)).getWinner() &&
                ticTacToes.get(Positions.getPositionFromNumber(firstCol)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(firstCol+6)).getWinner()){
            isFinished=true;
        }
    }

    private void checkLeftCross(Positions position){
        int row = (position.positionNumber / 3);
        int col = position.positionNumber % 3;

        if (row == col){
            if (ticTacToes.get(Positions.getPositionFromNumber(0)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(4)).getWinner() &&
                    ticTacToes.get(Positions.getPositionFromNumber(0)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(8)).getWinner()){
                isFinished=true;
            }
        }
    }

    private void checkRightCross(Positions position){
        int row = (position.positionNumber / 3);
        int col = position.positionNumber % 3;

        if (row+col == 2){
            if (ticTacToes.get(Positions.getPositionFromNumber(2)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(4)).getWinner() &&
                    ticTacToes.get(Positions.getPositionFromNumber(2)).getWinner() == ticTacToes.get(Positions.getPositionFromNumber(6)).getWinner()){
                isFinished=true;
            }
        }
    }

}

