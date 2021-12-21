import LobbyToPlayerPayload from "./LobbyToPlayerPayload";

export default interface LobbyPayload {

    id?: string,
    name?: string,
    started?: boolean,
    players?: LobbyToPlayerPayload[],
    gameField: number[][]

}