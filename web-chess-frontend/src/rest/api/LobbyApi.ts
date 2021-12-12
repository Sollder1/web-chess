import Rest from "../Rest";
import LobbyPayload from "../model/LobbyPayload";

class PlayerApi {

    public static async queryLobbies(query: string): Promise<LobbyPayload[]> {
        return Rest.getSpecific(`lobbies?query=${query}`);
    }

    public static async getLobby(id: string): Promise<LobbyPayload> {
        return Rest.getSpecific(`lobbies/${id}`);
    }

    public static async addLobby(lobby: LobbyPayload): Promise<LobbyPayload> {
        return Rest.post(lobby, `lobbies`);
    }
}

export default PlayerApi;