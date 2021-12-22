package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends Figure {


    private static final Set<Coordinate> POSSIBLE_DELTAS = new HashSet<>();

    static {
        POSSIBLE_DELTAS.add(new Coordinate(1, 1));
        POSSIBLE_DELTAS.add(new Coordinate(-1, -1));

        POSSIBLE_DELTAS.add(new Coordinate(0, 1));
        POSSIBLE_DELTAS.add(new Coordinate(0, -1));

        POSSIBLE_DELTAS.add(new Coordinate(1, 0));
        POSSIBLE_DELTAS.add(new Coordinate(-1, 0));

        POSSIBLE_DELTAS.add(new Coordinate(-1, 1));
        POSSIBLE_DELTAS.add(new Coordinate(1, -1));

    }


    //If true AND no valid moves -> Checkmate...!
    public boolean kingInCheck(byte[][] gameField, Color color) {
        byte[][] allPossibleMoves = getAllPossibleMoves(gameField, Color.Companion.notColor(color));
        for (byte y = 0; y < 8; y++) {
            for (byte x = 0; x < 8; x++) {
                if (allPossibleMoves[y][x] > 0) {
                    if (gameField[y][x] == KI_B || gameField[y][x] == KI_W);
                        return true;
                }
            }
        }
        return false;
    }


    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {

        List<Coordinate> validMoves = new ArrayList<>();

        for (Coordinate delta : POSSIBLE_DELTAS) {

            var targetX = delta.getX() + figurePosition.getX();
            var targetY = delta.getY() + figurePosition.getY();

            if (outOfBounds(targetX, targetY)) {
                continue;
            }

            var figureCode = gameField[figurePosition.getY()][figurePosition.getX()];
            var valueAtPos = gameField[targetY][targetX];

            if (isTargetFreeToMove(figureCode, valueAtPos)) {
                validMoves.add(new Coordinate(targetX, targetY));
            }
        }

        return validMoves;


        //TODO: Rochade...

    }

    private boolean isTargetFreeToMove(byte myCode, byte theirCode) {

        //Leeres Feld geht immer...
        if (theirCode == EM_F) {
            return true;
        }

        return isTargetAnEnemy(myCode, theirCode);
    }

}
