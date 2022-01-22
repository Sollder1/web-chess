package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Color;
import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import jakarta.inject.Inject;

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
    public static boolean kingInCheck(List<Move> moves, byte[][] gameFieldBefore, Move move, Color color) {

        var sourceFigureId = gameFieldBefore[move.getFrom().getY()][move.getFrom().getX()];
        var targetFigureId = gameFieldBefore[move.getTo().getY()][move.getTo().getX()];

        gameFieldBefore[move.getFrom().getY()][move.getFrom().getX()] = Figure.EM_F;
        gameFieldBefore[move.getTo().getY()][move.getTo().getX()] = sourceFigureId;

        boolean kingInCheck = kingInCheck(moves, gameFieldBefore, color);

        gameFieldBefore[move.getFrom().getY()][move.getFrom().getX()] = sourceFigureId;
        gameFieldBefore[move.getTo().getY()][move.getTo().getX()] = targetFigureId;


        return kingInCheck;
    }

    public static boolean kingInCheck(List<Move> moves, byte[][] gameFieldBefore, Color color) {
        byte[][] allPossibleMoves = getAttackedFields(moves, gameFieldBefore, Color.Companion.notColor(color));
        for (byte y = 0; y < 8; y++) {
            for (byte x = 0; x < 8; x++) {
                if (allPossibleMoves[y][x] > 0) {
                    if (gameFieldBefore[y][x] == KI_B || gameFieldBefore[y][x] == KI_W) {
                        return true;
                    }
                }
            }
        }
        return false;
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

        //TODO: Rochade...


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
