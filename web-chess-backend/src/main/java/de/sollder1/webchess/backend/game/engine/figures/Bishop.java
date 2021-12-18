package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.engine.Player;

import java.util.List;

public class Bishop extends Figure {

    @Override
    public byte id(Player player) {
        return 0;
    }

    @Override
    public boolean isMoveValid(Move move, byte[][] gameField, boolean kingInCheck) {
        return getValidMoves(move.getFrom(), gameField, kingInCheck).contains(move.getTo());
    }
        /*
        byte currentFigure = gameField[move.getFrom().getX()][move.getFrom().getY()];

        byte x = 1;
        byte y = 1;
        if (move.getFrom().getX() > move.getTo().getX()) {
            x = -1;
        }
        if (move.getFrom().getY() > move.getTo().getY()) {
            y = -1;
        }

        byte i = (byte) (move.getFrom().getX() + x);
        byte j = (byte) (move.getFrom().getY() + y);

        while (i < FIELD_SIZE && i >= 0 && j < FIELD_SIZE && j >= 0) {
            if (gameField[i][j] != EMPTY__FIELD) {
                if (gameField[i][j] / Math.abs(gameField[i][j]) == currentFigure / Math.abs(currentFigure)) {
                    return false;
                }
                else {
                    return i == move.getTo().getX() && j == move.getTo().getY();
                }
            }
            else {
                if (i == move.getTo().getX() && j == move.getTo().getY()) {
                    return true;
                }
            }
            i += x;
            j += y;
        }

        return false;

    }
    */


    @Override
    public List<Coordinate> getValidMoves(Coordinate figurePosition, byte[][] gameField, boolean kingInCheck) {
        List<Coordinate> validMoves = null;
        byte figurePos = gameField[figurePosition.getX()][figurePosition.getY()];

        byte x = (byte) (figurePosition.getX() - 1);
        byte y = (byte) (figurePosition.getY() - 1);
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[x][y] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (gameField[x][y] / Math.abs(gameField[x][y]) == figurePos / Math.abs(figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            x--;
            y--;
        }

        x = (byte) (figurePosition.getX() - 1);
        y = (byte) (figurePosition.getY() + 1);
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[x][y] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (gameField[x][y] / Math.abs(gameField[x][y]) == figurePos / Math.abs(figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            x--;
            y++;
        }

        x = (byte) (figurePosition.getX() + 1);
        y = (byte) (figurePosition.getY() - 1);
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[x][y] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (gameField[x][y] / Math.abs(gameField[x][y]) == figurePos / Math.abs(figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            x++;
            y--;
        }

        x = (byte) (figurePosition.getX() + 1);
        y = (byte) (figurePosition.getY() + 1);
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[x][y] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (gameField[x][y] / Math.abs(gameField[x][y]) == figurePos / Math.abs(figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            x++;
            y++;
        }

        return validMoves;
    }
}
