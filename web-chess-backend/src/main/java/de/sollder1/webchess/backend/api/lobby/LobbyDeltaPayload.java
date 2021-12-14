package de.sollder1.webchess.backend.api.lobby;

public class LobbyDeltaPayload {

    //meta:
    private String lobbyId;
    private String playerId;

    //delta:
    private boolean start;


    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
