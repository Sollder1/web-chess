package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;

import java.util.List;

public class King extends Figure {


    //If true AND no valid moves -> Checkmate...!
    public boolean isCheck(byte[][] gameField, Player player) {
        //TODO...
        return false;
    }

    @Override
    public byte id(Player player) {
        return 0;
    }

    @Override
    public boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck) {
        return false;
    }

    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {
        return null;
    }
}