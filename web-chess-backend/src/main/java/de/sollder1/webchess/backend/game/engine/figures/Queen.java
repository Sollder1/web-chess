package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;

import java.util.List;

public class Queen extends Figure {
    @Override
    public byte id(Player player) {
        return 0;
    }

    @Override
    public boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck) {
        return FigureApi.getBehaviourModelById(Figure.BISHOP_BLACK).isMoveValid(move, gameField, kingInCheck)
                || FigureApi.getBehaviourModelById(Figure.CASTLE_BLACK).isMoveValid(move, gameField, kingInCheck);
    }

    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {
        return null;
    }
}
