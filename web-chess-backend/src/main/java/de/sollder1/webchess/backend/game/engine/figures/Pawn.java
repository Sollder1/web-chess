package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sollder1
 */
public class Pawn extends Figure {


    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {

        List<Coordinate> validMoves = new ArrayList<>();

        //1. Prüfe ob gegner

        var figureY = figurePosition.getY();
        var figureX = figurePosition.getX();

        var figureCode = gameField[figurePosition.getY()][figurePosition.getX()];
        var moveDirection = figureCode > 0 ? -1 : 1;


        var oneForwardY = figureY + moveDirection;
        //prüfe ob Bauuer eins nach vorne kann...
        if (!outOfBounds(figureX, oneForwardY) && gameField[oneForwardY][figureX] == EM_F) {
            validMoves.add(new Coordinate(figureX, oneForwardY));
        }

        //prüfe ob Bauuer zwei nach vorne kann... Out of bounds check ist nicht nötig
        var twoForwardY = figureY + moveDirection + moveDirection;
        var expectedYForTwoForward = figureCode > 0 ? 6 : 1;
        if (figureY == expectedYForTwoForward && gameField[oneForwardY][figureX] == EM_F && gameField[twoForwardY][figureX] == EM_F) {
            validMoves.add(new Coordinate(figureX, twoForwardY));
        }


        //Prüfe ob Bauer zwei nach vorne kann:
        var leftHitX = figureX - 1;
        var leftHitY = figureY + moveDirection;

        if (!outOfBounds(leftHitX, leftHitY) && isTargetAnEnemy(figureCode, gameField[leftHitY][leftHitX])) {
            validMoves.add(new Coordinate(leftHitX, leftHitY));
        }


        var rightHitX = figureX + 1;
        var rightHitY = figureY + moveDirection;

        if (!outOfBounds(rightHitX, rightHitY) && isTargetAnEnemy(figureCode, gameField[rightHitY][rightHitX])) {
            validMoves.add(new Coordinate(rightHitX, rightHitY));
        }


        return validMoves;
    }


}
