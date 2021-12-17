package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Player;

import static de.sollder1.webchess.backend.game.engine.figures.Figure.*;

import java.util.HashMap;
import java.util.Map;



public class FigureApi {

    private static final Map<Byte, Figure> INSTANCES;

    static {
        INSTANCES = new HashMap<>();

        var pawn = new Pawn();
        INSTANCES.put(PAWN___WHITE, pawn);
        INSTANCES.put(PAWN___BLACK, pawn);

        var king = new King();
        INSTANCES.put(KING___WHITE, king);
        INSTANCES.put(KING___BLACK, king);

        var queen = new Queen();
        INSTANCES.put(QUEEN__WHITE, queen);
        INSTANCES.put(QUEEN__BLACK, queen);

        var bishop = new Bishop();
        INSTANCES.put(BISHOP_WHITE, bishop);
        INSTANCES.put(BISHOP_BLACK, bishop);

        var castle = new Castle();
        INSTANCES.put(CASTLE_WHITE, castle);
        INSTANCES.put(CASTLE_BLACK, castle);

        var knight = new Knight();
        INSTANCES.put(KNIGHT_WHITE, knight);
        INSTANCES.put(KNIGHT_BLACK, knight);

    }

    public static Figure getBehaviourModelById(byte id) {
        return INSTANCES.get(id);
    }

}