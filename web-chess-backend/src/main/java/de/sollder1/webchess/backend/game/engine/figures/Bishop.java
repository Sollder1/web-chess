package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;

import java.util.List;

public class Bishop extends Figure {

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
