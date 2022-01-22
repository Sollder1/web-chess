package de.sollder1.webchess.backend.game.engine.figures;

import de.sollder1.webchess.backend.game.engine.Coordinate;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Castle extends Figure {

    @Override
    public List<Coordinate> getValidMovesImpl(List<Move> moves, Coordinate figurePosition, byte[][] gameField) {
        List<Coordinate> validMoves = new ArrayList<>();
        byte figurePos = gameField[figurePosition.getY()][figurePosition.getX()];

        byte x = (byte) (figurePosition.getX() - 1);
        byte y = (byte) (figurePosition.getY());
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[y][x] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (!isTargetAnEnemy(gameField[y][x], figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            x--;
        }

        x = (byte) (figurePosition.getX() + 1);
        y = (byte) (figurePosition.getY());
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[y][x] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (!isTargetAnEnemy(gameField[y][x], figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            x++;
        }

        x = (byte) (figurePosition.getX());
        y = (byte) (figurePosition.getY() - 1);
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[y][x] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (!isTargetAnEnemy(gameField[y][x], figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            y--;
        }

        x = (byte) (figurePosition.getX());
        y = (byte) (figurePosition.getY() + 1);
        while (x < FIELD_SIZE && x >= 0 && y < FIELD_SIZE && y >= 0) {
            if (gameField[y][x] == EM_F) {
                Coordinate nextCoordinate = new Coordinate(x, y);
                validMoves.add(nextCoordinate);
            }
            else {
                if (!isTargetAnEnemy(gameField[y][x], figurePos)) {
                    break;
                }
                else {
                    Coordinate nextCoordinate = new Coordinate(x, y);
                    validMoves.add(nextCoordinate);
                    break;
                }
            }
            y++;
        }

        return validMoves;
    }
}