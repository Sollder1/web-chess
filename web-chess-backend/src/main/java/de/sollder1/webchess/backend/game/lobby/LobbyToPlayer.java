package de.sollder1.webchess.backend.game.lobby;

import de.sollder1.webchess.backend.game.engine.Color;
import de.sollder1.webchess.backend.game.engine.Move;
import de.sollder1.webchess.backend.game.player.Player;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class LobbyToPlayer {

    public LobbyToPlayer() {
    }

    public LobbyToPlayer(Player player, Color playerColor) {
        this.player = player;
        this.connected = true;
        this.playerColor = playerColor;

        this.yourTurn = playerColor == Color.WHITE;

        updates = new ArrayBlockingQueue<>(16);
    }

    private Player player;
    private boolean connected;
    private boolean yourTurn;
    private Color playerColor;
    private Queue<LobbyPollData> updates;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Queue<LobbyPollData> getUpdates() {
        return updates;
    }

    public void setUpdates(Queue<LobbyPollData> updates) {
        this.updates = updates;
    }
}
