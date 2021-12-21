package de.sollder1.webchess.backend.game.lobby;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.sollder1.webchess.backend.game.engine.Move;

import java.util.List;
import java.util.Queue;

public class Lobby {

    private String id;
    private String name;
    private boolean started;
    private List<LobbyToPlayer> players;
    private byte[][] gameField;

    //TODO: Merken wie lange keien Figur geschmissen wurde... wenn == 40 -> Unentschieden!
    //TODO:


    //TODO, jsuzt quick and dirty...


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
