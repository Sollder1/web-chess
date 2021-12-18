import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.figures.Figure;
import de.sollder1.webchess.backend.game.engine.figures.FigureApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PawnTest {

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


    private final byte[][] killField = {
            {Figure.CA_B, Figure.KN_B, Figure.EM_F, Figure.KI_B, Figure.EM_F, Figure.BI_B, Figure.KN_B, Figure.CA_B},
            {Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.PW_B, Figure.EM_F, Figure.EM_F},
            {Figure.PA_W, Figure.BI_B, Figure.PA_W, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.EM_F, Figure.PW_B, Figure.QU_B, Figure.PW_B, Figure.EM_F},
            {Figure.EM_F, Figure.EM_F, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W, Figure.PA_W},
            {Figure.CA_W, Figure.KN_W, Figure.BI_W, Figure.KI_W, Figure.QU_W, Figure.BI_W, Figure.KN_W, Figure.CA_W},
    };


    @Test
    public void testStartPositionMoves() {

        var model = FigureApi.getBehaviourModelById(Figure.PA_W);

        //Tests black (black moves down):
        for (int x = 0; x < 8; x++) {
            var movesBlack = model.getValidMoves(new Coordinate(x, 1), startField, false);
            assertEquals(2, movesBlack.size());
            //Erster Zug:
            assertEquals(2, movesBlack.get(0).getY());
            assertEquals(x, movesBlack.get(0).getX());
            //Zweiter Zug:
            assertEquals(3, movesBlack.get(1).getY());
            assertEquals(x, movesBlack.get(1).getX());
        }

        //Tests white (White moves up):
        for (int x = 0; x < 8; x++) {
            var movesBlack = model.getValidMoves(new Coordinate(x, 6), startField, false);
            assertEquals(2, movesBlack.size());
            //Erster Zug:
            assertEquals(5, movesBlack.get(0).getY());
            assertEquals(x, movesBlack.get(0).getX());
            //Zweiter Zug:
            assertEquals(4, movesBlack.get(1).getY());
            assertEquals(x, movesBlack.get(1).getX());
        }
    }

    @Test
    public void testKill() {
        var model = FigureApi.getBehaviourModelById(Figure.PA_W);

        var movesBlack = model.getValidMoves(new Coordinate(1, 1), killField, false);
        assertEquals(2, movesBlack.size());
        //Erster Zug:
        assertEquals(2, movesBlack.get(0).getY());
        assertEquals(0, movesBlack.get(0).getX());
        //Zweiter Zug:
        assertEquals(2, movesBlack.get(1).getY());
        assertEquals(2, movesBlack.get(1).getX());

        var movesWhite = model.getValidMoves(new Coordinate(5, 6), killField, false);
        assertEquals(2, movesWhite.size());
        //Erster Zug:
        assertEquals(5, movesWhite.get(0).getY());
        assertEquals(4, movesWhite.get(0).getX());
        //Zweiter Zug:
        assertEquals(5, movesWhite.get(1).getY());
        assertEquals(6, movesWhite.get(1).getX());
    }

}