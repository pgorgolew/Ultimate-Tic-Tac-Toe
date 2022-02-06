package agh.ics.project2;

import java.util.*;

public class TicTacToe {
    private final Map<Positions, Signs> signs = new EnumMap<>(Positions.class);
    public boolean isFinished = false;
    private Signs winner = Signs.Nothing;

    public Signs getWinner() {
        return winner;
    }

    public TicTacToe() {
        Arrays.stream(Positions.values()).forEach(v-> signs.put(v, Signs.Nothing));
    }

    public void addSign(Signs sign, Positions position){
        signs.put(position, sign);
        checkIfFinished(position, sign);
    }

    public Signs getSignFromCell(Positions position){
        return signs.get(position);
    }

    private void checkIfFinished(Positions position, Signs sign){
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
        int takenPositions = (int) signs.values().stream().filter(e -> e != Signs.Nothing).count();
        if (takenPositions==9)
            isFinished = true;
    }

    private void checkRow(Positions position){
        int firstRowInt = (position.positionNumber / 3) * 3;

        if (signs.get(Positions.getPositionFromNumber(firstRowInt))== signs.get(Positions.getPositionFromNumber(firstRowInt+1)) &&
                signs.get(Positions.getPositionFromNumber(firstRowInt))== signs.get(Positions.getPositionFromNumber(firstRowInt+2))){
            isFinished=true;
        }
    }

    private void checkColumn(Positions position){
        int firstCol = position.positionNumber % 3;

        if (signs.get(Positions.getPositionFromNumber(firstCol))== signs.get(Positions.getPositionFromNumber(firstCol+3)) &&
                signs.get(Positions.getPositionFromNumber(firstCol))== signs.get(Positions.getPositionFromNumber(firstCol+6))){
            isFinished=true;
        }
    }

    private void checkLeftCross(Positions position){
        int row = (position.positionNumber / 3);
        int col = position.positionNumber % 3;

        if (row == col){
            if (signs.get(Positions.getPositionFromNumber(0))== signs.get(Positions.getPositionFromNumber(4)) &&
                    signs.get(Positions.getPositionFromNumber(0))== signs.get(Positions.getPositionFromNumber(8))){
                isFinished=true;
            }
        }
    }

    private void checkRightCross(Positions position){
        int row = (position.positionNumber / 3);
        int col = position.positionNumber % 3;

        if (row+col == 2){
            if (signs.get(Positions.getPositionFromNumber(2)) == signs.get(Positions.getPositionFromNumber(4)) &&
                    signs.get(Positions.getPositionFromNumber(2)) == signs.get(Positions.getPositionFromNumber(6))){
                isFinished=true;
            }
        }
    }
}

