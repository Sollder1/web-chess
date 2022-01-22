package de.sollder1.webchess.backend.game.lobby;

import de.sollder1.webchess.backend.game.engine.Move;

import java.util.Collections;
import java.util.List;

public class LobbyPollData {
    private Boolean started;
    private String currentPlayerId;
    private List<Move> newMoves;
    private LobbyToPlayer playerJoined;

    public LobbyPollData() {
    }

    public LobbyPollData(Move newMove) {
        newMoves = Collections.singletonList(newMove);
    }

    public LobbyPollData(Move newMove, String currentPlayerId) {
        this.newMoves = Collections.singletonList(newMove);
        this.currentPlayerId = currentPlayerId;
    }

    public LobbyPollData(Boolean started) {
        this.started = started;
    }

    public LobbyPollData(LobbyToPlayer newPlayer) {
        this.playerJoined = newPlayer;
    }


    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public String getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(String currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public List<Move> getNewMoves() {
        return newMoves;
    }

    public void setNewMoves(List<Move> newMoves) {
        this.newMoves = newMoves;
    }

    public LobbyToPlayer getPlayerJoined() {
        return playerJoined;
    }

    public void setPlayerJoined(LobbyToPlayer playerJoined) {
        this.playerJoined = playerJoined;
    }
}
