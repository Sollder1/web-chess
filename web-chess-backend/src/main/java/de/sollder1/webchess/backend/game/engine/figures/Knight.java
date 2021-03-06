package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sollder1
 */
public class Knight extends Figure {

    private static final Set<Coordinate> POSSIBLE_DELTAS = new HashSet<>();

    static {
        POSSIBLE_DELTAS.add(new Coordinate(2, 1));
        POSSIBLE_DELTAS.add(new Coordinate(2, -1));
        POSSIBLE_DELTAS.add(new Coordinate(-2, -1));
        POSSIBLE_DELTAS.add(new Coordinate(-2, 1));

        POSSIBLE_DELTAS.add(new Coordinate(1, 2));
        POSSIBLE_DELTAS.add(new Coordinate(-1, 2));
        POSSIBLE_DELTAS.add(new Coordinate(1, -2));
        POSSIBLE_DELTAS.add(new Coordinate(-1, -2));
    }

    @Override
    public List<Coordinate> getValidMovesImpl(List<Move> moves, Coordinate figurePosition, byte[][] gameField) {

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
    }

    private boolean isTargetFreeToMove(byte myCode, byte theirCode) {

        //Leeres Feld geht immer...
        if (theirCode == EM_F) {
            return true;
        }

        return isTargetAnEnemy(myCode, theirCode);
    }

}
