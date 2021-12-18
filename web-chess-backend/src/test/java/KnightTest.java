import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.figures.Figure;
import de.sollder1.webchess.backend.game.engine.figures.FigureApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class KnightTest {

    private final byte[][] startField = {
            {Figure.CA_B, Figure.KN_B, Figure.BI_B, Figure.KI_B, Figure.QU_B, Figure.BI_B, Figure.KN_B, Figure.CA_B},
            {Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W},
            {Figure.CA_W, Figure.KN_W, Figure.BI_W, Figure.KI_W, Figure.QU_W, Figure.BI_W, Figure.KN_W, Figure.CA_W},
    };

    @Test
    public void testStartPositionMoves() {

        var model = FigureApi.getBehaviourModelById(Figure.KN_B);


        //Black:
        var movesBlack = model.getValidMoves(new Coordinate(1, 0), startField, false);
        assertEquals(2, movesBlack.size());
        //Erster Zug:
        assertEquals(2, movesBlack.get(0).getY());
        assertEquals(2, movesBlack.get(0).getX());
        //Zweiter Zug:
        assertEquals(2, movesBlack.get(1).getY());
        assertEquals(0, movesBlack.get(1).getX());


        //White:
        var movesWhite = model.getValidMoves(new Coordinate(1, 7), startField, false);
        assertEquals(2, movesWhite.size());
        //Erster Zug:
        assertEquals(5, movesWhite.get(0).getY());
        assertEquals(0, movesWhite.get(0).getX());
        //Zweiter Zug:
        assertEquals(5, movesWhite.get(1).getY());
        assertEquals(2, movesWhite.get(1).getX());

    }


}
