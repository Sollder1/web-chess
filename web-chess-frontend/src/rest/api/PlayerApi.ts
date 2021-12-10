import Rest from "../Rest";
import PlayerPayload from "../model/PlayerPayload";

class PlayerApi {

    public static async getUser(id: string): Promise<PlayerPayload> {
        return Rest.getSpecific(`player/${id}`);
    }

    public static async createUser(payload: PlayerPayload): Promise<PlayerPayload> {
        return Rest.post(payload, `player`);
    }

}

export default PlayerApi;