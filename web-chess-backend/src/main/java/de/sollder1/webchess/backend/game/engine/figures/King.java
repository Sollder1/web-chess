package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Color;
import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends Figure {

    private static final Set<Coordinate> POSSIBLE_DELTAS = new HashSet<>();

    static {
        POSSIBLE_DELTAS.add(new Coordinate(1, 1));
        POSSIBLE_DELTAS.add(new Coordinate(-1, -1));

        POSSIBLE_DELTAS.add(new Coordinate(0, 1));
        POSSIBLE_DELTAS.add(new Coordinate(0, -1));

        POSSIBLE_DELTAS.add(new Coordinate(1, 0));
        POSSIBLE_DELTAS.add(new Coordinate(-1, 0));

        POSSIBLE_DELTAS.add(new Coordinate(-1, 1));
        POSSIBLE_DELTAS.add(new Coordinate(1, -1));

    }


    //If true AND no valid moves -> Checkmate...!
    public static boolean kingInCheck(List<Move> moves, byte[][] gameFieldBefore, Move move, Color color) {

        var sourceFigureId = gameFieldBefore[move.getFrom().getY()][move.getFrom().getX()];
        var targetFigureId = gameFieldBefore[move.getTo().getY()][move.getTo().getX()];

        gameFieldBefore[move.getFrom().getY()][move.getFrom().getX()] = Figure.EM_F;
        gameFieldBefore[move.getTo().getY()][move.getTo().getX()] = sourceFigureId;

        boolean kingInCheck = kingInCheck(moves, gameFieldBefore, color);

        gameFieldBefore[move.getFrom().getY()][move.getFrom().getX()] = sourceFigureId;
        gameFieldBefore[move.getTo().getY()][move.getTo().getX()] = targetFigureId;


        return kingInCheck;
    }

    public static boolean kingInCheck(List<Move> moves, byte[][] gameFieldBefore, Color color) {
        byte[][] allPossibleMoves = getAttackedFields(moves, gameFieldBefore, Color.Companion.notColor(color));
        for (byte y = 0; y < 8; y++) {
            for (byte x = 0; x < 8; x++) {
                if (allPossibleMoves[y][x] > 0) {
                    if (gameFieldBefore[y][x] == KI_B || gameFieldBefore[y][x] == KI_W) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public List<Coordinate> getValidMovesImpl(List<Move> moves, Coordinate figurePosition, byte[][] gameField) {

        List<Coordinate> validMoves = new ArrayList<>();

        var figureCode = gameField[figurePosition.getY()][figurePosition.getX()];

        for (Coordinate delta : POSSIBLE_DELTAS) {

            var targetX = delta.getX() + figurePosition.getX();
            var targetY = delta.getY() + figurePosition.getY();

            if (outOfBounds(targetX, targetY)) {
                continue;
            }

            var valueAtPos = gameField[targetY][targetX];

            if (isTargetFreeToMove(figureCode, valueAtPos)) {
                validMoves.add(new Coordinate(targetX, targetY));
            }
        }

        if (figureCode > 0){ //Rochade für Weiss
            if (figurePosition.getX() == 4 && figurePosition.getY() == 7){  //steht der König am Start
                if (neverMoved(moves, figurePosition)) {    //hat der König sich bewegt
                    if (gameField[7][1] == 0 && gameField[7][2] == 0 && gameField[7][3] == 0){  //sind die Felder links bis zum Turm frei
                        if (neverMoved(moves, new Coordinate(0,7))){    //hat der linke Turm sich nie bewegt
                            var currentColor = Color.Companion.getByFigureId(figureCode);
                            byte[][] attackedFields = getAttackedFields(moves, gameField, currentColor);
                            if (attackedFields[7][2] == 0 && attackedFields[7][3] == 0 && attackedFields[7][4] == 0) {  //zieht oder steht der König nicht im Schach
                                validMoves.add(new Coordinate(0, 7));
                            }
                        }
                    }
                    if (gameField[7][5] == 0 && gameField[7][6] == 0){  //sind die Felder rechts bis zum Turm frei
                        if (neverMoved(moves, new Coordinate(7,7))){    //hat der rechte Turm sich nie bewegt
                            var currentColor = Color.Companion.getByFigureId(figureCode);
                            byte[][] attackedFields = getAttackedFields(moves, gameField, currentColor);
                            if (attackedFields[7][4] == 0 && attackedFields[7][5] == 0 && attackedFields[7][6] == 0) {  //zieht oder steht der König nicht im Schach
                                validMoves.add(new Coordinate(7, 7));
                            }
                        }
                    }
                }
            }
        }
        else {  //Rochade für Schwarz
            if (figurePosition.getX() == 4 && figurePosition.getY() == 0){
                if (neverMoved(moves, figurePosition)) {    //hat der König sich bewegt
                    if (gameField[0][1] == 0 && gameField[0][2] == 0 && gameField[0][3] == 0){  //sind die Felder links bis zum Turm frei
                        if (neverMoved(moves, new Coordinate(0,0))){    //hat der linke Turm sich nie bewegt
                            var currentColor = Color.Companion.getByFigureId(figureCode);
                            byte[][] attackedFields = getAttackedFields(moves, gameField, currentColor);
                            if (attackedFields[0][2] == 0 && attackedFields[0][3] == 0 && attackedFields[0][4] == 0) {  //zieht oder steht der König nicht im Schach
                                validMoves.add(new Coordinate(0, 0));
                            }
                        }
                    }
                    if (gameField[0][5] == 0 && gameField[0][6] == 0){  //sind die Felder rechts bis zum Turm frei
                        if (neverMoved(moves, new Coordinate(7,0))){    //hat der rechte Turm sich nie bewegt
                            var currentColor = Color.Companion.getByFigureId(figureCode);
                            byte[][] attackedFields = getAttackedFields(moves, gameField, currentColor);
                            if (attackedFields[0][4] == 0 && attackedFields[0][5] == 0 && attackedFields[0][6] == 0) {  //zieht oder steht der König nicht im Schach
                                validMoves.add(new Coordinate(7, 0));
                            }
                        }
                    }
                }
            }
        }


        return validMoves;

    }

    private boolean neverMoved(List<Move> moves, Coordinate figurePosition) {
        for (var move : moves) {
            if (move.getFrom() == figurePosition) {
                return false;
            }
        }
        return true;
    }



    private boolean isTargetFreeToMove(byte myCode, byte theirCode) {

        //Leeres Feld geht immer...
        if (theirCode == EM_F) {
            return true;
        }

        return isTargetAnEnemy(myCode, theirCode);
    }

}
