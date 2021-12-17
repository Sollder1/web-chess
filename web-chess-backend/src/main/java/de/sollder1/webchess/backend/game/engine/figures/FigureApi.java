package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Player;

import java.util.HashMap;
import java.util.Map;

public class FigureApi {

    private static final Map<Byte, Figure> INSTANCES;

    static {
        INSTANCES = new HashMap<>();

        var pawn = new Pawn();
        INSTANCES.put(pawn.id(Player.WHITE), pawn);
        INSTANCES.put(pawn.id(Player.BLACK), pawn);

    }

    public static Figure getBehaviourModelById(byte id) {
        return INSTANCES.get(id);
    }

}