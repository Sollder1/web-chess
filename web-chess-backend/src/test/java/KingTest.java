import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.figures.Figure;
import de.sollder1.webchess.backend.game.engine.figures.FigureApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KingTest {

    private byte[][] startField = {
            {Figure.CA_B, Figure.KN_B, Figure.BI_B, Figure.KI_B, Figure.QU_B, Figure.BI_B, Figure.KN_B, Figure.CA_B},
            {Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W},
            {Figure.CA_W, Figure.KN_W, Figure.BI_W, Figure.KI_W, Figure.QU_W, Figure.BI_W, Figure.KN_W, Figure.CA_W},
    };

    private byte[][] customField = {
            {Figure.CA_B, Figure.KN_B, Figure.BI_B, Figure.KI_B, Figure.QU_B, Figure.BI_B, Figure.KN_B, Figure.CA_B},
            {Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.EM_F, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.PW_B, Figure.PA_W, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W},
            {Figure.CA_W, Figure.KN_W, Figure.BI_W, Figure.KI_W, Figure.QU_W, Figure.BI_W, Figure.KN_W, Figure.CA_W},
    };


    @Test
    public void testMoves() {

        Coordinate from = new Coordinate((byte) 0, (byte) 2);
        Coordinate to = new Coordinate((byte) 1, (byte) 2);
        assertFalse(!FigureApi.getBehaviourModelById(Figure.BI_B).isMoveValid(new Move(from, to), customField, false));
        assertEquals(1, FigureApi.getBehaviourModelById(Figure.KI_B).getValidMoves(new Coordinate(0, 3), customField, false));


    }

}
