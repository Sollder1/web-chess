package de.sollder1.webchess.backend.game.lobby;

import de.sollder1.webchess.backend.game.player.Player;

public class LobbyToPlayer {

    public LobbyToPlayer() {
    }

    public LobbyToPlayer(Player player) {
        this.player = player;
        this.connected = true;
    }

    private Player player;
    private boolean connected;

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
}
