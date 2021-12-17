package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;

import java.util.List;

public abstract class Figure {

    public static final byte FIELD_SIZE = 8;

    public static final byte EMPTY__FIELD = 0;

    public static final byte PAWN___WHITE = 2;
    public static final byte PAWN___BLACK = -2;
    public static final byte KNIGHT_WHITE = 7;
    public static final byte KNIGHT_BLACK = -7;
    public static final byte BISHOP_WHITE = 6;
    public static final byte BISHOP_BLACK = -6;
    public static final byte CASTLE_WHITE = 10;
    public static final byte CASTLE_BLACK = -10;
    public static final byte QUEEN__WHITE = 18;
    public static final byte QUEEN__BLACK = -18;
    public static final byte KING___WHITE = 127;
    public static final byte KING___BLACK = -127;



    public abstract byte id(Player player);

    public abstract boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck);

    public abstract List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck);

}
