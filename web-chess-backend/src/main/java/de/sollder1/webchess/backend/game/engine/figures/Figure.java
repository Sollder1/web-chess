package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;

import java.util.List;

public abstract class Figure {

    public static final byte FIELD_SIZE = 8;



    public static final byte EMPTY_FIELD = 0;
    public static final byte EM_F = 0;

    public static final byte PA_W = 2;
    public static final byte PW_B = -2;
    public static final byte KN_W = 7;
    public static final byte KN_B = -7;
    public static final byte BI_W = 6;
    public static final byte BI_B = -6;
    public static final byte CA_W = 10;
    public static final byte CA_B = -10;
    public static final byte QU_W = 18;
    public static final byte QU_B = -18;
    public static final byte KI_W = 127;
    public static final byte KI_B = -127;



    public abstract byte id(Player player);

    public abstract boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck);

    public abstract List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck);

}
