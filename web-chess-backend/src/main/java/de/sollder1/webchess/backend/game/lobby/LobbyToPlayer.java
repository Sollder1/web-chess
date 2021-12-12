package de.sollder1.webchess.backend.game.lobby;

import de.sollder1.webchess.backend.game.player.Player;

public class LobbyToPlayer {

    public LobbyToPlayer() {
    }

    public LobbyToPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
