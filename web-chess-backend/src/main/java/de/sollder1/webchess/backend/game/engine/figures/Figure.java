package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;
import javassist.bytecode.ByteArray;

import java.util.List;

public abstract class Figure {

    public abstract byte id(Player player);

    public abstract boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck);

    public abstract List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck);

}
