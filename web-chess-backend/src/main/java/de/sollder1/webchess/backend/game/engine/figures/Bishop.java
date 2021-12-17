package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;

import java.util.List;

public class Bishop extends Figure {

    @Override
    public byte id(Player player) {
        return 0;
    }

    @Override
    public boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck) {
        byte currentFigure = gameField[move.getFrom().getX()][move.getFrom().getY()];

        if (!(currentFigure == Figure.BISHOP_BLACK || currentFigure == Figure.BISHOP_WHITE)) {
            return false;
        }

        byte x = 1;
        byte y = 1;
        if (move.getFrom().getX() > move.getTo().getX()) {
            x = -1;
        }
        if (move.getFrom().getY() > move.getTo().getY()) {
            y = -1;
        }

        byte i = move.getFrom().getX();
        byte j = move.getFrom().getY();

        while (i < FIELD_SIZE && i >= 0 && j < FIELD_SIZE && j >= 0) {
            if (currentFigure > 0) {
                if (gameField[i][j] > 0) {
                    return false;
                }
            } else {
                if (gameField[i][j]/Math.abs(gameField[i][j]) == currentFigure/Math.abs(currentFigure)) {
                    return false;//i == move.getTo().getX() && j == move.getTo().getY();
                }
            }
            if (i == move.getTo().getX() && j == move.getTo().getY()) {
                return true;
            }
            i += x;
            j += y;
        }

        return false;

    }


    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {
        return null;
    }
}
