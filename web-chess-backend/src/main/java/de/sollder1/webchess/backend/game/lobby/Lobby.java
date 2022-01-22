package de.sollder1.webchess.backend.game.lobby;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.sollder1.webchess.backend.api.lobby.ByteArray2DSerializer;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    private String id;
    private String name;
    private boolean started;
    private List<LobbyToPlayer> players;
    @JsonSerialize(using = ByteArray2DSerializer.class)
    private byte[][] gameField;
    private final List<Move> moves = new ArrayList<>();


    //TODO: Merken wie lange keien Figur geschmissen wurde... wenn == 40 -> Unentschieden!
    //TODO:



    public void addMove(Move move) {
        this.moves.add(move);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public List<LobbyToPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<LobbyToPlayer> players) {
        this.players = players;
    }

    public byte[][] getGameField() {
        return gameField;
    }

    public void setGameField(byte[][] gameField) {
        this.gameField = gameField;
    }
}
