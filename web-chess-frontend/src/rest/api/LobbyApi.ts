import Rest from "../Rest";
import LobbyPayload from "../model/LobbyPayload";
import LobbyDeltaPayload from "../model/LobbyDeltaPayload";
import PollPayload from "../model/PollPayload";

class LobbyApi {

    public static async queryLobbies(query: string): Promise<LobbyPayload[]> {
        return Rest.getSpecific(`lobbies?query=${query}`);
    }

    public static async getLobby(id: string): Promise<LobbyPayload> {
        return Rest.getSpecific(`lobbies/${id}`);
    }

    public static async addLobby(lobby: LobbyPayload): Promise<LobbyPayload> {
        return Rest.post(lobby, `lobbies`);
    }

    public static async startLobby(delta: LobbyDeltaPayload): Promise<LobbyDeltaPayload> {
        return Rest.put(delta, `lobbies`);
    }

    public static async joinLobby(lobbyId: string | undefined, playerId: string | undefined): Promise<LobbyPayload> {
        return Rest.put({}, `lobbies/${lobbyId}/players/${playerId}`);
    }

    public static async poll(lobbyId: string | undefined, playerId: string | undefined): Promise<PollPayload> {
        return Rest.getSpecific(`lobbies/${lobbyId}/updates/${playerId}`);
    }

}

export default LobbyApi;