package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Player;

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
    public boolean isCheck(byte[][] gameField, Player player) {
        //TODO...
        return false;
    }

    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {


        return null;
    }
}
