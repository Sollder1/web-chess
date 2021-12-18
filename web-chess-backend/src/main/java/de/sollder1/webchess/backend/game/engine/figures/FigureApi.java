package de.sollder1.webchess.backend.game.engine.figures;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static de.sollder1.webchess.backend.game.engine.figures.Figure.*;


public class FigureApi {

    private static final Map<Byte, Figure> INSTANCES;

    static {
        INSTANCES = new HashMap<>();

        var pawn = new Pawn();
        INSTANCES.put(PA_W, pawn);
        INSTANCES.put(PW_B, pawn);

        var king = new King();
        INSTANCES.put(KI_W, king);
        INSTANCES.put(KI_B, king);

        var queen = new Queen();
        INSTANCES.put(QU_W, queen);
        INSTANCES.put(QU_B, queen);

        var bishop = new Bishop();
        INSTANCES.put(BI_W, bishop);
        INSTANCES.put(BI_B, bishop);

        var castle = new Castle();
        INSTANCES.put(CA_W, castle);
        INSTANCES.put(CA_B, castle);

        var knight = new Knight();
        INSTANCES.put(KN_W, knight);
        INSTANCES.put(KN_B, knight);

    }

    public static Figure getBehaviourModelById(byte id) {
        return INSTANCES.get(id);
    }

    public static byte[][] getStartGameField() {
        return new byte[][]{
                {Figure.CA_B, Figure.KN_B, Figure.BI_B, Figure.KI_B, Figure.QU_B, Figure.BI_B, Figure.KN_B, Figure.CA_B},
                {Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B},
                {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
                {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
                {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
                {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
                {Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W},
                {Figure.CA_W, Figure.KN_W, Figure.BI_W, Figure.KI_W, Figure.QU_W, Figure.BI_W, Figure.KN_W, Figure.CA_W},
        };
    }
}