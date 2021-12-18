package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.List;

/**
 * @author sollder1
 */
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


    public boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck) {
        return getValidMoves(move.getFrom(), gameField, kingInCheck).contains(move.getTo());
    }

    public abstract List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck);


    protected static boolean outOfBounds(int value) {
        return value >= FIELD_SIZE || value < 0;
    }

    protected static boolean outOfBounds(int x, int y) {
        return outOfBounds(x) || outOfBounds(y);
    }

    protected boolean isTargetAnEnemy(int myCode, int theirCode) {

        if (theirCode == KI_W || theirCode == KI_B) {
            return false;
        }

        //Wenn ich positiv bin muss zielfeld negativ sein
        if (myCode > 0 && theirCode < 0) {
            return true;
        }

        //Wenn ich negativ bin muss zielfeld postiv sein
        return myCode < 0 && theirCode > 0;
    }


}
