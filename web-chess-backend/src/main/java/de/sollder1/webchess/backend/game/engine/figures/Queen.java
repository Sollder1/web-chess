package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;

import java.util.Collections;
import java.util.List;

public class Queen extends Figure {

    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {
        var list = FigureApi.getBehaviourModelById(BI_B).getValidMoves(figurePosition, gameField, kingInCheck);
        list.addAll(FigureApi.getBehaviourModelById(CA_B).getValidMoves(figurePosition, gameField, kingInCheck));
        return list;
    }
}
