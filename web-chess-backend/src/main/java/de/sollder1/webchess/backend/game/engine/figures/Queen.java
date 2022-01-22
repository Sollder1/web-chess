package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.List;

public class Queen extends Figure {

    @Override
    public List<Coordinate> getValidMovesImpl(List<Move> moves, Coordinate figurePosition, byte[][] gameField) {
        var list = FigureApi.getBehaviourModelById(BI_B).getValidMovesImpl(moves, figurePosition, gameField);
        list.addAll(FigureApi.getBehaviourModelById(CA_B).getValidMovesImpl(moves, figurePosition, gameField));
        return list;
    }
}
