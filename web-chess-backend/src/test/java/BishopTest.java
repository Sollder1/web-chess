import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.figures.Figure;
import de.sollder1.webchess.backend.game.engine.figures.FigureApi;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BishopTest {

    private byte[][] startField = {
            {Figure.CASTLE_BLACK, Figure.KNIGHT_BLACK, Figure.BISHOP_BLACK, Figure.KING___BLACK, Figure.QUEEN__BLACK, Figure.BISHOP_BLACK, Figure.KNIGHT_BLACK, Figure.CASTLE_BLACK},
            {Figure.PAWN___BLACK, Figure.PAWN___BLACK, Figure.PAWN___BLACK, Figure.PAWN___BLACK, Figure.PAWN___BLACK, Figure.PAWN___BLACK, Figure.PAWN___BLACK, Figure.PAWN___BLACK},
            {Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD},
            {Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD},
            {Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD},
            {Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD, Figure.EMPTY__FIELD},
            {Figure.PAWN___WHITE, Figure.PAWN___WHITE, Figure.PAWN___WHITE, Figure.PAWN___WHITE, Figure.PAWN___WHITE, Figure.PAWN___WHITE, Figure.PAWN___WHITE, Figure.PAWN___WHITE},
            {Figure.CASTLE_WHITE, Figure.KNIGHT_WHITE, Figure.BISHOP_WHITE, Figure.KING___WHITE, Figure.QUEEN__WHITE, Figure.BISHOP_WHITE, Figure.KNIGHT_WHITE, Figure.CASTLE_WHITE},
    };


    @Test
    public void testMoves() {

        Coordinate from = new Coordinate((byte) 0, (byte) 2);
        Coordinate to = new Coordinate((byte) 2, (byte) 4);

        assertFalse(FigureApi.getBehaviourModelById(Figure.BISHOP_BLACK).isMoveValid(new Move(from, to), startField, false));


    }

}
